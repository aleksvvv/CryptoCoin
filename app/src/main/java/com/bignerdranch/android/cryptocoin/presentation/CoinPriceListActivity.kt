package com.bignerdranch.android.cryptocoin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.cryptocoin.databinding.ActivityCoinPrceListBinding
import com.bignerdranch.android.cryptocoin.domain.CoinInfo
import com.bignerdranch.android.cryptocoin.presentation.adapter.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinPrceListBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

}
