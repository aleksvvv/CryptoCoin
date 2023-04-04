package com.bignerdranch.android.cryptocoin.presentation

import android.app.Application
import androidx.work.Configuration
import com.bignerdranch.android.cryptocoin.data.database.AppDatabase
import com.bignerdranch.android.cryptocoin.data.di.DaggerApplicationComponent
import com.bignerdranch.android.cryptocoin.data.mapper.CoinMapper
import com.bignerdranch.android.cryptocoin.data.network.ApiFactory
import com.bignerdranch.android.cryptocoin.data.worker.RefreshDataWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    @Inject
    lateinit var refreshDataWorkerFactory: RefreshDataWorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                refreshDataWorkerFactory
            ).build()
    }
}