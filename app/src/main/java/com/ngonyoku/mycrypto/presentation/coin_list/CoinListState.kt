package com.ngonyoku.mycrypto.presentation.coin_list

import com.ngonyoku.mycrypto.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)