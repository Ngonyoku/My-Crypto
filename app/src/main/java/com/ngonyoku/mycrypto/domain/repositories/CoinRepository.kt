package com.ngonyoku.mycrypto.domain.repositories

import com.ngonyoku.mycrypto.data.remote.dto.CoinDetailDto
import com.ngonyoku.mycrypto.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}