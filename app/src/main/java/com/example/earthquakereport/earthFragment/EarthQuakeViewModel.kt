package com.example.earthquakereport.earthFragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import com.example.earthquakereport.database.EarthQuakeDatabase
import com.example.earthquakereport.repository.EarthquakeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

const val START_TIME = "2020-01-01"
const val END_TIME = "2020-04-20"
const val MAGNITUDE_LIMIT = 6
const val FORMAT = "geojson"


class EarthQuakeViewModel(
    earthQuakeDatabase: EarthQuakeDatabase,
    private val application: Application
) :
    ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val repository = EarthquakeRepository(earthQuakeDatabase)


    private fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val info = connectivityManager.activeNetworkInfo ?: return false
            return info.isConnected
        }
    }

    init {
        if (isOnline()) {
            coroutineScope.launch {
                repository.refreshNetworkWithDatabase()
            }
        }
    }

    val earthQuake = repository.earthQuakeList

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}