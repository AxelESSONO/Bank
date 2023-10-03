package com.axel.bank.presentation.bank.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.R
import com.axel.bank.domain.model.Bank
import com.axel.bank.util.totalBalance
import com.bumptech.glide.Glide

class BankAdapter(private var banks : ArrayList<Bank>) : RecyclerView.Adapter<BankAdapter.BankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bank_item, parent, false)
        return BankViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bindView(bank = banks[position])
    }

    override fun getItemCount(): Int {
        return banks.size
    }

    fun setData(banks: ArrayList<Bank>) {
        this.banks = banks
        notifyDataSetChanged()
    }


    inner class BankViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val bankHeader: TextView = view.findViewById(R.id.bankHeader)
        private val bankAccountTitle: TextView = view.findViewById(R.id.bankAccountTitle)
        private val amountBankText: TextView = view.findViewById(R.id.amountBankText)
        private val collapsibleImageButton: ImageView = view.findViewById(R.id.collapsibleImageButton)
        private val accountRecyclerView : RecyclerView = view.findViewById(R.id.accountRecycler)


        private lateinit var accountAdapter : AccountAdapter
        private var accountManager : LinearLayoutManager = LinearLayoutManager(collapsibleImageButton.context)

        private var isExtended : Boolean = false

        fun bindView(bank: Bank){
            bankAccountTitle.text = bank.title

            amountBankText.text = "${totalBalance(bank.accounts)}"
            bankHeader.text = bank.name
            if (bank.header){
                bankHeader.visibility = View.VISIBLE
            }else{
                bankHeader.visibility = View.GONE
            }

            collapsibleImageButton.setOnClickListener {
                if (isExtended){
                    Glide.with(view.context)
                        .load(R.drawable.round_keyboard_arrow_up_24)
                        .into(collapsibleImageButton)

                    accountAdapter = AccountAdapter(bank.accounts)
                    accountManager.orientation = LinearLayoutManager.VERTICAL
                    accountRecyclerView.apply {
                        layoutManager = accountManager
                        adapter = accountAdapter
                        setHasFixedSize(true)
                    }

                }else{
                    Glide.with(view.context)
                        .load(R.drawable.round_keyboard_arrow_down_24)
                        .into(collapsibleImageButton)
                }
                isExtended = !isExtended
            }
        }
    }
}