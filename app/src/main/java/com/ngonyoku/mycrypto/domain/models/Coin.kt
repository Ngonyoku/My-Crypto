package com.ngonyoku.mycrypto.domain.models

/**
 * This is the data that will be passed down to the views/UI Components
 * */
data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)