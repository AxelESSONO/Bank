package com.axel.bank.data.repository

import com.axel.bank.data.data_source.BankAPI
import com.axel.bank.data.data_source.dto.BankDTOItem
import com.axel.bank.domain.repository.BankRepository
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val bankAPI: BankAPI
) : BankRepository {
    override suspend fun getAllBanks(): List<BankDTOItem> {
        return bankAPI.getAllBanks()
    }

}