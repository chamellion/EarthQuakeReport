package com.example.earthquakereport.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EarthQuakeDAO {

    @Query("SELECT * FROM earthquakeDatabase")
    fun getAllEarthQuakes(): LiveData<List<DataBaseEarthQuake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEarthQuake(vararg earthQuake: DataBaseEarthQuake)
}