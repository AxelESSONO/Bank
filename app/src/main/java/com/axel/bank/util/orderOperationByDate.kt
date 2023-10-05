package com.axel.bank.util

import com.axel.bank.domain.model.Operation

fun orderOperationByDate(operations: List<Operation>): List<Operation> {

   /* var first = 1
    var second = 0

    val sendOperations = mutableListOf<Operation>()
    val newOperations = operations

    operations.sortedByDescending { it.date }.apply {
        if (operations.size >= 2) {
            for (op in 1 until operations.size) {
                if (operations[op].date == operations[op - 1].date) {
                    val operationsTmp = mutableListOf<Operation>(
                        operations[op - 1],
                        operations[op]
                    ).sortedBy { it.title }
                    sendOperations.add(operationsTmp[0])
                    sendOperations.add(operationsTmp[1])
                } else {
                    val operationsTmpSecond = mutableListOf<Operation>(
                        operations[op - 1],
                        operations[op]
                    ).sortedByDescending { it.date }
                    sendOperations.add(operationsTmpSecond[0])
                    sendOperations.add(operationsTmpSecond[1])
                }
            }

        }else{
            sendOperations.addAll(operations)
        }
    }*/

    val operationMap = operations.groupBy { it.date }
    var operationsDate = operationMap.toSortedMap().keys.sortedDescending()
    var sendOperations = mutableListOf<Operation>()

    operationsDate.forEach { date->
        operationMap[date]?.sortedBy{ it.title }?.let { sendOperations.addAll(it.toMutableList()) }
    }


    return sendOperations
}












