package com.bignerdranch.android.cryptocoin.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.cryptocoin.data.repository.CoinRepositoryImpl
import com.bignerdranch.android.cryptocoin.domain.GetCoinInfoListUseCase
import com.bignerdranch.android.cryptocoin.domain.GetCoinInfoUseCase
import com.bignerdranch.android.cryptocoin.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repositoryImpl)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repositoryImpl)
    private val loadDataUseCase = LoadDataUseCase(repositoryImpl)

    val coinInfoList = getCoinInfoListUseCase()
    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }


}