package com.axel.bank.util

import com.axel.bank.domain.model.Bank
import com.axel.bank.domain.model.BankStickyHeader

fun Bank.ConvertBankListToStickyList(banks : List<Bank>) : List<BankStickyHeader>{

    val bankStickyHeaders = mutableListOf<BankStickyHeader>()

    /*banks.forEach { bank ->

        if (bank.isCA){
            bankStickyHeaders.add(
                BankStickyHeader(
                    bankHeaderName = Constants.CREDIT_AGRICOLE_STICKY,
                    accounts = bank.accounts
                )
            )
        }
    }

    bankStickyHeaders.forEach { bankStickyHeader ->
        bankStickyHeader.accounts?.sortedBy { it.title }
    }*/

    return bankStickyHeaders

}

/*fun SeparateCreditAgricoleToOverBank() : List<BankStickyHeader>{

}*/


































