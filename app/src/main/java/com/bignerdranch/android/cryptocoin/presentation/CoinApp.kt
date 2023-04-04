package com.bignerdranch.android.cryptocoin.presentation

import android.app.Application
import com.bignerdranch.android.cryptocoin.data.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}