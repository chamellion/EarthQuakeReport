package com.example.earthquakereport.domain

data class EarthQuakeDomain(
    val mag: Double,
    val place: String,
    val time: Long,
    val url: String
)