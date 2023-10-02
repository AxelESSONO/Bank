package com.axel.bank.util

sealed class BankResponseState<T>(val data :T?=null,val message:String?=null) {
    class Loading<T>(data:T?=null) : BankResponseState<T>(data)
    class Success<T>(data:T) : BankResponseState<T>(data)
    class Error<T>(message:String,data:T?=null) : BankResponseState<T>(data,message)
}