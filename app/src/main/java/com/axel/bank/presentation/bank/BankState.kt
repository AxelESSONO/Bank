package com.axel.bank.presentation.bank

import com.axel.bank.domain.model.Bank

data class BankState(
    val isLoading : Boolean = false,
    val banks : List<Bank> = emptyList(),
    val error : String = ""
)
