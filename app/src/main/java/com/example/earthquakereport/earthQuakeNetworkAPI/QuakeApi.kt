package com.example.earthquakereport.earthQuakeNetworkAPI

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/"

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit : Retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create(moshi))
                            .addCallAdapterFactory(CoroutineCallAdapterFactory())
                            .build()

//interface for retrofit
interface QuakeApi{

    @GET("query")
    fun getEarthQuakes(@Query("format") format: String,
                        @Query("starttime") starttime : String,
                        @Query("endtime") endtime : String,
                         @Query("minmagnitude")minmag : Int) : Deferred<ResponseData>
}

object QuakeApiService{
    val getEarthQuakeNow : QuakeApi by lazy {
        retrofit.create(QuakeApi::class.java)
    }
}



