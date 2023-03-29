package com.bignerdranch.android.cryptocoin.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameDto (
    @SerializedName("Name")
    val name: String? = null
)
