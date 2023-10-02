package com.axel.bank.data.data_source.dto


import com.google.gson.annotations.SerializedName

data class BankDTOItem(
    @SerializedName("accounts")
    val accounts: List<Account>,

    @SerializedName("isCA")
    val isCA: Int,

    @SerializedName("name")
    val name: String
)