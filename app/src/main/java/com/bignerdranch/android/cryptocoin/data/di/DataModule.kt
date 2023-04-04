package com.bignerdranch.android.cryptocoin.data.di

import android.app.Application
import com.bignerdranch.android.cryptocoin.data.database.AppDatabase
import com.bignerdranch.android.cryptocoin.data.database.CoinInfoDao
import com.bignerdranch.android.cryptocoin.data.network.ApiFactory
import com.bignerdranch.android.cryptocoin.data.network.ApiService
import com.bignerdranch.android.cryptocoin.data.repository.CoinRepositoryImpl
import com.bignerdranch.android.cryptocoin.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bidsRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @ApplicationScope
        @Provides
        fun providesCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }

    }
}