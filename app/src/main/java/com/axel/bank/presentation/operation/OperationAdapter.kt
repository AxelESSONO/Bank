package com.axel.bank.presentation.operation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.R
import com.axel.bank.domain.model.Operation

class OperationAdapter(private val operations : List<Operation>) : RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.operation_item, parent, false)
        return OperationViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        holder.bindView(operations[position])
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    class OperationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val operationTitle : TextView = view.findViewById(R.id.operationTitle)
        private val operationDate : TextView = view.findViewById(R.id.operationDate)
        private val operationAmount : TextView = view.findViewById(R.id.operationAmount)
        fun bindView(operation: Operation){
            operationTitle.text = operation.title
            operationDate.text = operation.date
            operationAmount.text = operation.amount
        }
    }
}