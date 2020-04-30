package com.example.earthquakereport.earthQuakeNetworkAPI

import com.squareup.moshi.Json

data class ResponseData(val features : List<Features>)

data class Features(@field:Json(name = "properties") val properties : Properties)


data class Properties( @field: Json(name = "mag") val mag: Double,
                       @field: Json(name = "place")val place: String,
                       @field: Json(name = "time")val time : Long,
                       @field: Json(name = "url")val url : String,
                       @field: Json(name = "ids") val ids : String)
