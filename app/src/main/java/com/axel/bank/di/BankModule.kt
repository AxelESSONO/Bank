package com.axel.bank.di

import com.axel.bank.data.data_source.BankAPI
import com.axel.bank.data.repository.BankRepositoryImpl
import com.axel.bank.domain.repository.BankRepository
import com.axel.bank.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BankModule {

    @Provides
    @Singleton
    fun provideBankApi() : BankAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideBankRepository(bankAPI: BankAPI) : BankRepository{
        return BankRepositoryImpl(bankAPI = bankAPI)
    }

}