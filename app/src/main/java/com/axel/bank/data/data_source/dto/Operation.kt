package com.axel.bank.data.data_source.dto


import com.google.gson.annotations.SerializedName

data class Operation(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String
)