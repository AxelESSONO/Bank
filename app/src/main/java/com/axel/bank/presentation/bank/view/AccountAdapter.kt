package com.axel.bank.presentation.bank.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.R
import com.axel.bank.domain.model.Account
import com.axel.bank.presentation.operation.OperationActivity
import com.google.gson.Gson

class AccountAdapter(private val accounts : List<Account>?) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_item, parent, false)
        return AccountViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accounts?.size ?: 0
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        accounts?.let { holder.bindView(it[position]) }
    }

    inner class AccountViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val accountTitle: TextView = view.findViewById(R.id.accountTitle)
        private val amountAccountText: TextView = view.findViewById(R.id.amountAccountText)
        private val operationButton: ImageView = view.findViewById(R.id.operationButton)

        fun bindView(account: Account){
            accountTitle.text = account.title
            amountAccountText.text = account.balance.toString()
            operationButton.setOnClickListener {
                val operationIntent = Intent(operationButton.context, OperationActivity::class.java)
                operationIntent.putExtra("OPERATIONS", Gson().toJson(account.operations.sortedBy { it.date }))
                operationIntent.putExtra("AMOUNT", account.balance)
                operationIntent.putExtra("TITLE", account.title)
                operationButton.context.startActivity(operationIntent)
            }
        }
    }
}