package com.bignerdranch.android.cryptocoin.domain

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() = repository.loadData()
}