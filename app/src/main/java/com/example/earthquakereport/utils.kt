package com.example.earthquakereport

import com.example.earthquakereport.database.DataBaseEarthQuake
import com.example.earthquakereport.earthQuakeNetworkAPI.EarthQuake
import com.example.earthquakereport.earthQuakeNetworkAPI.ResponseData

fun ResponseData.asEarthQuake(): List<EarthQuake> {
    return features.map {
        EarthQuake(
            mag = it.properties.mag,
            place = it.properties.place,
            time = it.properties.time,
            url = it.properties.url
        )
    }
}

fun ResponseData.asDatabaseModel(): Array<DataBaseEarthQuake> {
    return features.map {
        DataBaseEarthQuake(
            mag = it.properties.mag,
            place = it.properties.place,
            time = it.properties.time,
            url = it.properties.url
        )
    }.toTypedArray()
}