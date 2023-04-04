package com.bignerdranch.android.cryptocoin.presentation

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.cryptocoin.domain.GetCoinInfoListUseCase
import com.bignerdranch.android.cryptocoin.domain.GetCoinInfoUseCase
import com.bignerdranch.android.cryptocoin.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()
    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}