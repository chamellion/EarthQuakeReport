package com.example.earthquakereport.earthQuakeNetworkAPI

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseData(val features : List<Features>)

@JsonClass(generateAdapter = true)
data class Features(val properties: EarthQuake)

@JsonClass(generateAdapter = true)
data class EarthQuake(
    val mag: Double,
    val place: String,
    val time: Long,
    val url: String
)


