package com.axel.bank.data.data_source

import com.axel.bank.data.data_source.dto.Account
import retrofit2.http.GET

interface BankAPI {

    @GET("banks.json")
    suspend fun getAllAccounts() : List<Account>

}