package com.axel.bank.domain.repository

import com.axel.bank.data.data_source.dto.BankDTOItem

interface BankRepository {
    suspend fun getAllBanks() : List<BankDTOItem>

    //suspend fun getAllAccountByBankName(name : String) : AccountDTO

}