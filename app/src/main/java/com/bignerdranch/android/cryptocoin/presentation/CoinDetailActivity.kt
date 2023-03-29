package com.bignerdranch.android.cryptocoin.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.cryptocoin.R
import com.bignerdranch.android.cryptocoin.data.network.ApiFactory
import com.bignerdranch.android.cryptocoin.databinding.ActivityCoinDetailBinding
import com.bignerdranch.android.cryptocoin.utils.convertTimestampToTime
import com.squareup.picasso.Picasso


class CoinDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
            binding.tvPrice.text = it.price
            binding.tvMinPrice.text = it.lowDay
            binding.tvMaxPrice.text = it.highDay
            binding.tvLastMarket.text = it.lastMarket
            binding.tvLastUpdate.text = convertTimestampToTime(it.lastUpdate)
            binding.tvFromSymbol.text = it.fromSymbol
            binding.tvToSymbol.text = it.toSymbol
            Picasso.get().load((ApiFactory.BASE_IMAGE_URL + it.imageUrl)).into(binding.ivLogoCoin)
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
