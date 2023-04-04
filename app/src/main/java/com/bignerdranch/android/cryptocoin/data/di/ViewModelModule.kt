package com.bignerdranch.android.cryptocoin.data.di

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.cryptocoin.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindViewModel(viewModel: CoinViewModel):ViewModel

}