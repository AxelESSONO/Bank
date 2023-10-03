package com.axel.bank.util

import android.util.Log
import com.axel.bank.domain.model.Operation
import com.axel.bank.presentation.operation.OperationActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


fun OperationActivity.convertJsonListToListOperation(key : String) : List<Operation>{
    val intent = intent
    val listJson = intent.getStringExtra(key)
    val operations = mutableListOf<Operation>()


    // Check for null and handle errors
    if (listJson != null) {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Operation?>?>() {}.type
        val myList: List<Operation> = gson.fromJson(listJson, type)

        // Now, you have your list in the receiving activity
        for (item in myList) {
           operations.add(item)
        }
        return operations
    }

    return emptyList()
}