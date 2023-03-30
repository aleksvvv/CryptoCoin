package com.bignerdranch.android.cryptocoin.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.cryptocoin.domain.CoinInfo

object CoinInfoDiffCallback:DiffUtil.ItemCallback<CoinInfo>()
 {
  override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
   return oldItem.fromSymbol == newItem.fromSymbol

  }

  override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
   return oldItem == newItem
  }
 }