package com.axel.bank.presentation.bank.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.R
import com.axel.bank.databinding.BankHeaderBinding
import com.axel.bank.databinding.BankItemBinding
import com.axel.bank.domain.model.Bank
import com.axel.bank.util.StickyHeaderItemDecoration.*
import com.axel.bank.util.totalBalance
import com.bumptech.glide.Glide

const val TYPE_HEADER = 0
const val TYPE_ITEM = 1
class BankAdapter(private val context: Context, private var banks : ArrayList<Bank>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = if (viewType == TYPE_HEADER){
            BankHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else{
            BankItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return if (viewType == TYPE_HEADER){
            BankViewHolderHeader(binding as BankHeaderBinding)
        }else{
            BankItemViewHolder(binding as BankItemBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is BankItemViewHolder) {
            holder.bindBankItem(context, banks[position])
        } else if(holder is BankViewHolderHeader) {
            holder.bindHeader(banks[position])
        }
    }

    override fun getItemCount(): Int {
        return banks.size
    }

    fun setData(banks: ArrayList<Bank>) {
        this.banks = banks
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(banks[position].header) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    inner class BankItemViewHolder(private val binding: BankItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var accountAdapter : AccountAdapter
        private var accountManager : LinearLayoutManager = LinearLayoutManager(binding.collapsibleImageButton.context)
        private var isExtended : Boolean = true

        @SuppressLint("SetTextI18n")
        fun bindBankItem(context: Context, bank: Bank){

            binding.bankAccountTitle.text = bank.title
            binding.amountBankText.text = "${totalBalance(bank.accounts)?.toLong()} euros"
            accountAdapter = AccountAdapter(bank.accounts)
            accountManager.orientation = LinearLayoutManager.VERTICAL
            binding.accountRecycler.apply {
                layoutManager = accountManager
                adapter = accountAdapter
                setHasFixedSize(true)
            }
            binding.accountRecycler.visibility = View.GONE
            isExtended = binding.accountRecycler.isVisible

            binding.collapsibleImageButton.setOnClickListener {
                if (isExtended){
                    Glide.with(context)
                        .load(R.drawable.round_keyboard_arrow_down_24)
                        .into(binding.collapsibleImageButton)
                    binding.accountRecycler.visibility = View.GONE
                }else{
                    Glide.with(context)
                        .load(R.drawable.round_keyboard_arrow_up_24)
                        .into(binding.collapsibleImageButton)
                    binding.accountRecycler.visibility = View.VISIBLE
                }
                isExtended = binding.accountRecycler.isVisible
            }
        }
    }

    inner class BankViewHolderHeader(private val binding: BankHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindHeader(bank: Bank){
            binding.headerView.text = bank.name
        }
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        var position = itemPosition
        do {
            if (this.isHeader(position)) {
                headerPosition = position
                break
            }
            position -= 1
        } while (position >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.bank_header
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        ((header as ConstraintLayout).getChildAt(0) as TextView).text =
            banks[headerPosition].name
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return banks[itemPosition].header
    }
}