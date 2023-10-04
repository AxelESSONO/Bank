package com.axel.bank.domain.model

data class Bank(
    val name: String?,
    val accounts: List<Account>?,
    val title : String = "",
    var header : Boolean = false
)
