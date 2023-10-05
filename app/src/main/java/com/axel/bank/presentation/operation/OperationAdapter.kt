package com.axel.bank.presentation.operation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.databinding.OperationItemBinding
import com.axel.bank.domain.model.Operation
import com.axel.bank.util.getDateTime

class OperationAdapter(private val operations : List<Operation>) : RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {

        val binding = OperationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OperationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        holder.bindView(operations[position])
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    class OperationViewHolder(private val binding: OperationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(operation: Operation){
            binding.operationTitle.text = operation.title
            binding.operationDate.text = getDateTime(operation.date.toString())
            binding.operationAmount.text = "${operation.amount} euros"
        }
    }
}