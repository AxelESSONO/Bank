package com.axel.bank.util

import com.axel.bank.domain.model.Account

fun totalBalance(accounts : List<Account>?) : Double{

    var sumBalance = 0.0

    accounts?.forEach {
        sumBalance += it.balance
    }

    return sumBalance
}