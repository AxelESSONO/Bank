package com.axel.bank.presentation.bank.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.R
import com.axel.bank.databinding.AccountItemBinding
import com.axel.bank.domain.model.Account
import com.axel.bank.domain.model.Bank
import com.axel.bank.presentation.operation.OperationActivity
import com.axel.bank.util.orderOperationByDate
import com.google.gson.Gson
import java.io.Serializable

class AccountAdapter(private val accounts : List<Account>?) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {

        val binding = AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AccountViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return accounts?.size ?: 0
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        accounts?.let { holder.bindView(it[position]) }
    }

    inner class AccountViewHolder(private val binding: AccountItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindView(account: Account){
            binding.accountTitle.text = account.title
            binding.amountAccountText.text = "${account.balance} euros"
            binding.operationButton.setOnClickListener {
                val operationIntent = Intent(binding.operationButton.context, OperationActivity::class.java)

                operationIntent.putExtra("OPERATIONS", orderOperationByDate((account.operations)) as Serializable)
                operationIntent.putExtra("AMOUNT", account.balance)
                operationIntent.putExtra("TITLE", account.title)

                binding.operationButton.context.startActivity(operationIntent)
            }
        }
    }
}