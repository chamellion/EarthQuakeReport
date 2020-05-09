package com.example.earthquakereport

import android.app.Application
import androidx.work.*
import com.example.earthquakereport.work.PeriodicWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class EarthquakeApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        setUpWork()
    }

    private fun setUpWork() {
        applicationScope.launch {
            setRecurringWork()
        }
    }

    private fun setRecurringWork() {
        val constraint = Constraints.Builder().setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val periodicWork = PeriodicWorkRequestBuilder<PeriodicWork>(1, TimeUnit.DAYS)
            .setConstraints(constraint).build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                PeriodicWork.WORK_MANGER,
                ExistingPeriodicWorkPolicy.REPLACE, periodicWork
            )
    }
}