package com.bignerdranch.android.cryptocoin.presentation

import android.app.Application
import androidx.work.Configuration
import com.bignerdranch.android.cryptocoin.data.database.AppDatabase
import com.bignerdranch.android.cryptocoin.data.di.DaggerApplicationComponent
import com.bignerdranch.android.cryptocoin.data.mapper.CoinMapper
import com.bignerdranch.android.cryptocoin.data.network.ApiFactory
import com.bignerdranch.android.cryptocoin.data.worker.RefreshDataWorkerFactory

class CoinApp : Application(),Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    CoinMapper(),
                    ApiFactory.apiService
                )
            ).build()
    }
}