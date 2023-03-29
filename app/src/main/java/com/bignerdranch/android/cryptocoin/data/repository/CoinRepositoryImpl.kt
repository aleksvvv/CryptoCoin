package com.bignerdranch.android.cryptocoin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.bignerdranch.android.cryptocoin.data.database.AppDatabase
import com.bignerdranch.android.cryptocoin.data.mapper.CoinMapper
import com.bignerdranch.android.cryptocoin.data.network.ApiFactory
import com.bignerdranch.android.cryptocoin.domain.CoinInfo
import com.bignerdranch.android.cryptocoin.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(private val application: Application) : CoinRepository {
    private val base = AppDatabase.getInstance(application)
    private val coinInfoDao = base.coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

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

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList =
                    mapper.mapJsonContainerToListCoinInfoDto(jsonContainer)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(1000)
        }
    }
}