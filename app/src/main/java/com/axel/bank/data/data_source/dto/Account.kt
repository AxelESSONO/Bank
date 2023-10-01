package com.axel.bank.data.data_source.dto


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("contract_number")
    val contractNumber: String,
    @SerializedName("holder")
    val holder: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("operations")
    val operations: List<Operation>,
    @SerializedName("order")
    val order: Int,
    @SerializedName("product_code")
    val productCode: String,
    @SerializedName("role")
    val role: Int
)