package com.axel.bank.domain.model

import java.io.Serializable

data class Operation(
    val id : String,
    val title : String,
    val amount : String,
    val date : Long
) : Serializable
