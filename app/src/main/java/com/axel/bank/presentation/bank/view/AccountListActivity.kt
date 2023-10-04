package com.axel.bank.presentation.bank.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axel.bank.databinding.ActivityAccountListBinding
import com.axel.bank.domain.model.Bank
import com.axel.bank.presentation.bank.viewmodel.BankViewModel
import com.axel.bank.presentation.operation.OperationActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class AccountListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountListBinding
    private val bankViewModel: BankViewModel by viewModels()
    private lateinit var bankLayoutManager: LinearLayoutManager
    private lateinit var bankAdapter: BankAdapter
    private var tempBankList = arrayListOf<Bank>()
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTheRecyclerView()
        callAPI()

        /*binding.bankRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (bankLayoutManager.findLastVisibleItemPosition() == bankLayoutManager.itemCount - 1) {
                    page += 1
                    bankViewModel.getAllBank()
                    callAPI()
                }
            }
        })*/
    }

    private fun setUpTheRecyclerView() {

        bankLayoutManager = LinearLayoutManager(this)
        bankLayoutManager.orientation = LinearLayoutManager.VERTICAL
        bankAdapter = BankAdapter(this ,ArrayList())
        binding.bankRecycler.apply {
            layoutManager = bankLayoutManager
            adapter = bankAdapter
            setHasFixedSize(true)
        }
    }

    private fun callAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            bankViewModel.getAllBank()
            bankViewModel._bankValue.collectLatest { bankListValue ->
                withContext(Dispatchers.Main){

                    if (bankListValue.isLoading) {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.myAccountsText.visibility = View.GONE

                    } else {
                        if (bankListValue.error.isNotBlank()) {
                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(this@AccountListActivity, bankListValue.error, Toast.LENGTH_LONG).show()
                        } else {

                            binding.myAccountsText.visibility = View.VISIBLE
                            binding.progressCircular.visibility = View.GONE
                            tempBankList.addAll(bankListValue.banks)
                            bankAdapter.setData(tempBankList)
                        }
                    }
                }
            }
        }
    }
}