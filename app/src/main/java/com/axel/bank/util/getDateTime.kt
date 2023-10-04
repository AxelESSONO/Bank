package com.axel.bank.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun getDateTime(s: String): String? {

    val time : Long = s.toLong()
    val format = "dd MMM yyyy" // you can add the format you need
    val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
    sdf.timeZone = TimeZone.getDefault() // set anytime zone you need

    return sdf.format(Date(time * 1000))
}