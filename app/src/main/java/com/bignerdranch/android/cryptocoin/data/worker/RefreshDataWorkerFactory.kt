package com.bignerdranch.android.cryptocoin.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.bignerdranch.android.cryptocoin.data.database.CoinInfoDao
import com.bignerdranch.android.cryptocoin.data.mapper.CoinMapper
import com.bignerdranch.android.cryptocoin.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val mapper: CoinMapper,
    private val apiService: ApiService
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
       return RefreshDataWorker(
           appContext,
           workerParameters,
           coinInfoDao,
           mapper,
           apiService
       )
    }
}