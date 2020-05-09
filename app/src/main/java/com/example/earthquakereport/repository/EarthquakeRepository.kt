package com.example.earthquakereport.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.earthquakereport.asDatabaseModel
import com.example.earthquakereport.database.EarthQuakeDatabase
import com.example.earthquakereport.database.asDomain
import com.example.earthquakereport.domain.EarthQuakeDomain
import com.example.earthquakereport.earthFragment.FORMAT
import com.example.earthquakereport.earthFragment.MAGNITUDE_LIMIT
import com.example.earthquakereport.earthFragment.START_TIME
import com.example.earthquakereport.earthQuakeNetworkAPI.QuakeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class EarthquakeRepository(private val earthQuakeDatabase: EarthQuakeDatabase) {

    val earthQuakeList: LiveData<List<EarthQuakeDomain>> = Transformations.map(
        earthQuakeDatabase.earthQuakeDAO.getAllEarthQuakes()
    ) {
        it.asDomain()
    }

    suspend fun refreshNetworkWithDatabase() {
        withContext(Dispatchers.IO) {
            val date = Date()
            val endTime = convertDate(date)
            val earthQuakeFromNetwork = QuakeApiService.getEarthQuakeNow.getEarthQuakes(
                FORMAT,
                START_TIME,
                endTime,
                MAGNITUDE_LIMIT,
                ""
            ).await()
            earthQuakeDatabase.earthQuakeDAO.insertEarthQuake(*earthQuakeFromNetwork.asDatabaseModel())
        }
    }

    private fun convertDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd").format(date)
    }
}