package com.ngonyoku.mycrypto.presentation.coin_detail

import com.ngonyoku.mycrypto.domain.models.Coin
import com.ngonyoku.mycrypto.domain.models.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)