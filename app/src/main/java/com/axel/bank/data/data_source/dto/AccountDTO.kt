package com.axel.bank.data.data_source.dto


import com.axel.bank.domain.model.Account
import com.axel.bank.domain.model.Operation
import com.google.gson.annotations.SerializedName

data class AccountDTO(
    @SerializedName("balance") val balance: Double,

    @SerializedName("contract_number") val contractNumber: String,

    @SerializedName("holder") val holder: String,

    @SerializedName("id") val id: String,

    @SerializedName("label") val label: String,

    @SerializedName("operations") val operations: List<OperationDTO>,

    @SerializedName("order") val order: Int,

    @SerializedName("product_code") val productCode: String,

    @SerializedName("role") val role: Int
){
    fun toAccount() : Account{
        val operationsTmp = mutableListOf<Operation>()
        for (operation in operations){
            operationsTmp .add(operation.toOperation())
        }
        return Account(
            title = label,
            balance = balance
            //operations = operations
        )
    }
}