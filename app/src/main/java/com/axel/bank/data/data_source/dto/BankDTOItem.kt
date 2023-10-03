package com.axel.bank.data.data_source.dto


import com.axel.bank.domain.model.Account
import com.axel.bank.domain.model.Bank
import com.axel.bank.domain.model.Operation
import com.axel.bank.util.Constants
import com.google.gson.annotations.SerializedName

data class BankDTOItem(
    @SerializedName("accounts")
    val accountDTOs: List<AccountDTO>?,

    @SerializedName("isCA")
    val isCA: Int,

    @SerializedName("name")
    val name: String
){
    fun toBank() : Bank{

        val accounts = mutableListOf<Account>()
        accountDTOs?.forEach {
            accounts.add(
                Account(
                    title = it.label,
                    balance = it.balance,
                    operations = convertOperationDtoToOperation(it.operations)
                )
            )
        }

        return Bank(
            name = if (toBooleanValue(isCA)) Constants.CREDIT_AGRICOLE_STICKY else Constants.OTHER_BANKS,
            //isCA = toBooleanValue(isCA),
            accounts = accounts,
            title = name
        )
    }

    private fun toBooleanValue(value : Int) : Boolean{
        if (value == 1){
            return true
        }
        return false
    }

    private fun convertOperationDtoToOperation(operationDTOs : List<OperationDTO>?) : List<Operation>{
        val operations = mutableListOf<Operation>()
        operationDTOs?.forEach {
            operations.add(
                Operation(
                    id = it.id,
                    title = it.title,
                    amount = it.amount,
                    date = it.date
                )
            )
        }
        return operations
    }

}