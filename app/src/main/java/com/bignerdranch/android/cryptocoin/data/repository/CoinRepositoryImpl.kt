package com.bignerdranch.android.cryptocoin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.bignerdranch.android.cryptocoin.data.RefreshDataWorker
import com.bignerdranch.android.cryptocoin.data.database.AppDatabase
import com.bignerdranch.android.cryptocoin.data.database.CoinInfoDao
import com.bignerdranch.android.cryptocoin.data.mapper.CoinMapper
import com.bignerdranch.android.cryptocoin.domain.CoinInfo
import com.bignerdranch.android.cryptocoin.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper : CoinMapper,
    private val coinInfoDao: CoinInfoDao
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            RefreshDataWorker.NAME_WORK,
            ExistingWorkPolicy.KEEP,
            RefreshDataWorker.makeRequest()
        )

//        while (true) {
//            try {
//                val topCoins = apiService.getTopCoinsInfo(limit = 50)
//                val fSyms = mapper.mapNamesListToString(topCoins)
//                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
//                val coinInfoDtoList =
//                    mapper.mapJsonContainerToListCoinInfoDto(jsonContainer)
//                val dbModelList = coinInfoDtoList.map {
//                    mapper.mapDtoToDbModel(it)
//                }
//                coinInfoDao.insertPriceList(dbModelList)
//            } catch (e: Exception) {
//            }
//            delay(1000)
//        }
    }
}