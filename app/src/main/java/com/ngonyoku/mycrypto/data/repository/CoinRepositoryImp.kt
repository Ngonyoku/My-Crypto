package com.ngonyoku.mycrypto.data.repository

import com.ngonyoku.mycrypto.data.remote.CoinPaprikaAPI
import com.ngonyoku.mycrypto.data.remote.dto.CoinDetailDto
import com.ngonyoku.mycrypto.data.remote.dto.CoinDto
import com.ngonyoku.mycrypto.domain.repositories.CoinRepository
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val api: CoinPaprikaAPI
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}