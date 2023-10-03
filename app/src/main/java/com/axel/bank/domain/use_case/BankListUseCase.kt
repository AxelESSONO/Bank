package com.axel.bank.domain.use_case

import android.util.Log
import com.axel.bank.domain.model.Bank
import com.axel.bank.domain.repository.BankRepository
import com.axel.bank.util.BankResponseState
import com.axel.bank.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BankListUseCase @Inject constructor(
    private val repository: BankRepository
) {
    operator fun invoke() : Flow<BankResponseState<List<Bank>>> = flow {
        try {
            emit(BankResponseState.Loading<List<Bank>>())
            var banks = repository.getAllBanks().map {
                it.toBank()
            }
            banks.elementAt(getFirstCreditAgricolePosition(banks)).header = true
            banks.elementAt(getFirstOtherBank(banks)).header = true

            emit(BankResponseState.Success<List<Bank>>(banks))
        }catch (e : HttpException){
            emit(BankResponseState.Error<List<Bank>>(e.localizedMessage ?: "An Unexpected Error"))
        }catch (e : IOException){
            emit(BankResponseState.Error<List<Bank>>("Internet Error"))
        }
    }

    private fun getFirstCreditAgricolePosition(banks : List<Bank>) : Int{
        var positionCA = 0
        banks.forEachIndexed { index, bank ->
            if (bank.name == Constants.CREDIT_AGRICOLE_STICKY){
                positionCA = index
                return positionCA
            }
        }
        return -1
    }

    private fun getFirstOtherBank(banks : List<Bank>) : Int{
        var positionCA = 0
        banks.forEachIndexed { index, bank ->
            if (bank.name == Constants.OTHER_BANKS){
                positionCA = index
                return positionCA
            }
        }
        return -1
    }

}