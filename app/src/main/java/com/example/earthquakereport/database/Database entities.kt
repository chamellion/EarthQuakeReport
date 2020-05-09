package com.example.earthquakereport.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.earthquakereport.domain.EarthQuakeDomain

@Entity(tableName = "earthquakeDatabase")
data class DataBaseEarthQuake(
    val mag: Double,
    val place: String,
    val time: Long,
    @PrimaryKey
    val url: String
)

fun List<DataBaseEarthQuake>.asDomain(): List<EarthQuakeDomain> {
    return map {
        EarthQuakeDomain(
            mag = it.mag,
            place = it.place,
            time = it.time,
            url = it.url
        )
    }
}

