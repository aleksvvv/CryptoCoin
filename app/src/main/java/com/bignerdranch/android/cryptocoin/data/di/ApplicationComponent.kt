package com.bignerdranch.android.cryptocoin.data.di

import android.app.Application
import com.bignerdranch.android.cryptocoin.presentation.CoinDetailFragment
import com.bignerdranch.android.cryptocoin.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}