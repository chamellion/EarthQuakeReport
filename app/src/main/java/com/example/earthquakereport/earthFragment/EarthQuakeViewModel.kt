package com.example.earthquakereport.earthFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquakereport.earthQuakeNetworkAPI.Features
import com.example.earthquakereport.earthQuakeNetworkAPI.Properties
import com.example.earthquakereport.earthQuakeNetworkAPI.QuakeApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val START_TIME = "2020-01-01"
const val END_TIME = "2020-04-20"
const val MAGNITUDE_LIMIT = 6
const val FORMAT = "geojson"

class EarthQuakeViewModel : ViewModel(){

    private val _earthQuakeList = MutableLiveData<List<Properties>>()
    val earthQuakeList : LiveData<List<Properties>>
    get() = _earthQuakeList

    private val _status = MutableLiveData<String>()
    val status : LiveData<String>
    get() = _status

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getEarthQuakeFromNetwork()
    }

    private fun getEarthQuakeFromNetwork() {
        coroutineScope.launch {
            val deferred = QuakeApiService.getEarthQuakeNow.getEarthQuakes(FORMAT, START_TIME,
                END_TIME, MAGNITUDE_LIMIT)
            try {
                val list = deferred.await()
                val propertyList: List<Properties> = extractFeatures(list.features)
                _earthQuakeList.value = propertyList
            }catch (T : Throwable){
                Log.i("EarthQuakeViewModel", "error" + T.message)
            }
        }
    }

    private fun extractFeatures(featuresList: List<Features>) : MutableList<Properties>{
        val mute = mutableListOf<Properties>()
        for (Property in featuresList){
         Property.let {
             val mut = Properties(it.properties.mag, it.properties.place,
                 it.properties.time, it.properties.url, it.properties.ids)
             mute.add(mut)
         }
        }
        return mute
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}