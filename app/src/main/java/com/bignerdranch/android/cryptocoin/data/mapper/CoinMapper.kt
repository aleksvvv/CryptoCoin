package com.bignerdranch.android.cryptocoin.data.mapper

import com.bignerdranch.android.cryptocoin.data.database.CoinInfoDbModel
import com.bignerdranch.android.cryptocoin.data.network.ApiFactory
import com.bignerdranch.android.cryptocoin.data.network.model.CoinInfoDto
import com.bignerdranch.android.cryptocoin.data.network.model.CoinInfoJsonContainerDto
import com.bignerdranch.android.cryptocoin.data.network.model.CoinNamesListDto
import com.bignerdranch.android.cryptocoin.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDbModel(coinInfoDto: CoinInfoDto): CoinInfoDbModel{
        return CoinInfoDbModel(
            fromSymbol = coinInfoDto.fromSymbol,
            toSymbol = coinInfoDto.toSymbol,
            price = coinInfoDto.price,
            lastUpdate = coinInfoDto.lastUpdate,
            highDay = coinInfoDto.highDay,
            lowDay = coinInfoDto.lowDay,
            lastMarket = coinInfoDto.lastMarket,
            imageUrl = coinInfoDto.imageUrl
        )
      }

    fun mapJsonContainerToListCoinInfoDto(jsonContainer: CoinInfoJsonContainerDto):
    List<CoinInfoDto>{
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String{
        return  namesListDto.names?.map {
                    it.coinNameDto?.name }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(coinInfoDbModel: CoinInfoDbModel):CoinInfo{
        return CoinInfo(
            fromSymbol = coinInfoDbModel.fromSymbol,
            toSymbol = coinInfoDbModel.toSymbol,
            price = coinInfoDbModel.price,
            lastUpdate = coinInfoDbModel.lastUpdate,
            highDay = coinInfoDbModel.highDay,
            lowDay = coinInfoDbModel.lowDay,
            lastMarket = coinInfoDbModel.lastMarket,
            imageUrl = coinInfoDbModel.imageUrl
        )
    }
}