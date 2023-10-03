package com.axel.bank.domain.use_case

import com.axel.bank.data.data_source.dto.BankDTO
import com.axel.bank.domain.model.Account
import com.axel.bank.domain.repository.BankRepository
import com.axel.bank.util.BankResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val repository: BankRepository
) {
    operator fun invoke() : Flow<BankResponseState<List<Account>>> = flow {
        try {
            emit(BankResponseState.Loading<List<Account>>())
            val accounts = repository.getAllBanks()
        }catch (e : HttpException){
            emit(BankResponseState.Error<List<Account>>(e.localizedMessage ?: "An Unexpected Error"))
        }catch (e : IOException){
            emit(BankResponseState.Error<List<Account>>("Internet Error"))
        }
    }
}