package com.axel.bank.domain.model

data class Bank(
    val name : String,
    val balance : Double,
    val account: List<Account>
)
