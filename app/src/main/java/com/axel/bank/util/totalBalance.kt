package com.axel.bank.util

import com.axel.bank.domain.model.Account
import java.math.RoundingMode
import java.text.DecimalFormat

fun totalBalance(accounts: List<Account>?): Double? {
    val sumBalance = accounts?.map { it.balance }?.sum()
    val decimalFormat = DecimalFormat("#.##")
    decimalFormat.roundingMode = RoundingMode.DOWN

    val roundBalance = decimalFormat.format(sumBalance)
    return roundBalance.toDouble()
}