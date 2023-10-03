package com.axel.bank.data.data_source

import com.axel.bank.data.data_source.dto.AccountDTO
import com.axel.bank.data.data_source.dto.BankDTOItem
import retrofit2.http.Field
import retrofit2.http.GET

interface BankAPI {

    @GET("banks.json")
    suspend fun getAllBanks() : List<BankDTOItem>

 /*   @GET("banks.json")
    suspend fun getAllAccountsByBankName(
        @Field("accounts") accounts : List<AccountDTO>
    ) : AccountDTO*/


}