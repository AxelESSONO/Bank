package com.axel.bank.data.data_source.dto


import com.axel.bank.domain.model.Operation
import com.google.gson.annotations.SerializedName

data class OperationDTO(
    @SerializedName("amount") val amount: String,

    @SerializedName("category") val category: String,

    @SerializedName("date") val date: String,

    @SerializedName("id") val id: String,

    @SerializedName("title") val title: String
){
    fun toOperation() : Operation{
        return Operation(
            id = id,
            title = title,
            amount = amount,
            date = date
        )
    }
}