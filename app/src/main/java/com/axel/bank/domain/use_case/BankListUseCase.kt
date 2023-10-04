package com.axel.bank.domain.use_case

import android.util.Log
import com.axel.bank.domain.model.Account
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
            var banks = repository.getAllBanks().map {it.toBank() }
            emit(BankResponseState.Success<List<Bank>>(groupBanksCaAndOther(banks)))
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

    private fun groupBanksCaAndOther(banks: List<Bank>): List<Bank> {

        val caBanks : MutableList<Bank> = mutableListOf(
            Bank(
                name = Constants.CREDIT_AGRICOLE_STICKY,
                accounts = null,
                title = "",
                header = true
            )
        )

        val otherBanks : MutableList<Bank> = mutableListOf(
            Bank(
                name = Constants.OTHER_BANKS,
                accounts = null,
                title = "",
                header = true
            )
        )

        caBanks.addAll(banks.filter { it.name == Constants.CREDIT_AGRICOLE_STICKY }.toMutableList())
        otherBanks.addAll(banks.filter { it.name == Constants.OTHER_BANKS }.toMutableList())



        return merge(
            sortBankAccountByAlphabeticalOrder(caBanks),
            sortBankAccountByAlphabeticalOrder(otherBanks)
        )
    }

    private fun <T> merge(first: List<T>, second: List<T>): List<T> {
        return first + second
    }

    private fun sortBankAccountByAlphabeticalOrder(banks: List<Bank>) : List<Bank>{
        return banks.sortedBy { it.title }
    }

}























































































