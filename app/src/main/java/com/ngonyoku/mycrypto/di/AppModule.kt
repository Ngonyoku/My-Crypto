package com.ngonyoku.mycrypto.di

import com.ngonyoku.mycrypto.common.Constants
import com.ngonyoku.mycrypto.data.remote.CoinPaprikaAPI
import com.ngonyoku.mycrypto.data.repository.CoinRepositoryImp
import com.ngonyoku.mycrypto.domain.repositories.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //They live as long as the app
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaAPI(): CoinPaprikaAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaAPI): CoinRepository {
        return CoinRepositoryImp(api)
    }
}
