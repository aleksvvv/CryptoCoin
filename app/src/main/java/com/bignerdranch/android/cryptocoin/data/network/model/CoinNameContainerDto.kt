package com.bignerdranch.android.cryptocoin.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto (
    @SerializedName("CoinInfo")
    val coinNameDto: CoinNameDto? = null
)
