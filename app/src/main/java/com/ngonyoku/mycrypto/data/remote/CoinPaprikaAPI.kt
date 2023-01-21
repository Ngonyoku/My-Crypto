package com.ngonyoku.mycrypto.data.remote

import com.ngonyoku.mycrypto.data.remote.dto.CoinDetailDto
import com.ngonyoku.mycrypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the different functions and routes from the API
 * */
interface CoinPaprikaAPI {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto> //Returns a list of coins

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailDto
}