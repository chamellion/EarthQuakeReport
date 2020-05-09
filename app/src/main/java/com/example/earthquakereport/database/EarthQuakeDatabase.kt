package com.example.earthquakereport.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataBaseEarthQuake::class], version = 1, exportSchema = false)
abstract class EarthQuakeDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var Instance: EarthQuakeDatabase? = null

        fun getInstance(context: Context): EarthQuakeDatabase {
            synchronized(this) {
                var instance = Instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        EarthQuakeDatabase::class.java, "EarthQuakeDatabase"
                    ).build()
                    Instance = instance
                }
                return instance
            }

        }
    }

    abstract val earthQuakeDAO: EarthQuakeDAO
}