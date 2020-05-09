package com.example.earthquakereport.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.earthquakereport.database.EarthQuakeDatabase
import com.example.earthquakereport.repository.EarthquakeRepository
import retrofit2.HttpException

class PeriodicWork(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {


    companion object {
        const val WORK_MANGER: String = "earthQuakeWorkManager"

    }


    override suspend fun doWork(): Result {
        val database = EarthQuakeDatabase.getInstance(applicationContext)
        val repository = EarthquakeRepository(database)
        return try {
            repository.refreshNetworkWithDatabase()
            Result.success()
        } catch (error: HttpException) {
            Result.retry()
        }
    }

}

