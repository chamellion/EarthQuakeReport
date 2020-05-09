package com.example.earthquakereport.earthFragment

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.earthquakereport.database.EarthQuakeDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class EarthQuakeViewModelFactory(
    private val earthQuakeDatabase: EarthQuakeDatabase,
    private val application: Application
) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EarthQuakeViewModel::class.java)) {
            return EarthQuakeViewModel(earthQuakeDatabase, application) as T
        }
        throw IllegalArgumentException("ViewModel cannot be created")
    }
}