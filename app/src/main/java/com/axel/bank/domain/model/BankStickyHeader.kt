package com.axel.bank.domain.model

data class BankStickyHeader(
    val bankHeaderName : String? = "",
    val accounts : List<Account>? = emptyList()
)
