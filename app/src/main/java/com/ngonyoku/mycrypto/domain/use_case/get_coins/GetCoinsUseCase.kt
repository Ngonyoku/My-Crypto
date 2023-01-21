package com.ngonyoku.mycrypto.domain.use_case.get_coins

import com.ngonyoku.mycrypto.common.Resource
import com.ngonyoku.mycrypto.data.remote.dto.toCoin
import com.ngonyoku.mycrypto.domain.models.Coin
import com.ngonyoku.mycrypto.domain.repositories.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class  GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository //Inject the interface and NOT the implementation
) {
    //Use cases should only have 1 public function
    //This method will allow us to all the GetCoinsUseCase class as a function
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error has occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        }
    }
}