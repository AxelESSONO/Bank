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


@AndroidEntryPoint
class AccountListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountListBinding
    private val bankViewModel: BankViewModel by viewModels()
    private lateinit var bankLayoutManager: LinearLayoutManager
    private lateinit var bankAdapter: BankAdapter
    private val tempBankList = arrayListOf<Bank>()
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bankLayoutManager = LinearLayoutManager(this)
        bankLayoutManager.orientation = LinearLayoutManager.VERTICAL
        setUpTheRecyclerView()
        callAPI()

        binding.bankRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (bankLayoutManager.findLastVisibleItemPosition() == bankLayoutManager.itemCount - 1) {
                    page += 1
                    bankViewModel.getAllBank()
                    callAPI()
                }
            }
        })
    }

    private fun setUpTheRecyclerView() {
        bankAdapter = BankAdapter(ArrayList())
        binding.bankRecycler.layoutManager = bankLayoutManager
        binding.bankRecycler.adapter = bankAdapter
        binding.bankRecycler.setHasFixedSize(true)
    }

    private fun callAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            bankViewModel.getAllBank()
            bankViewModel._bankValue.collectLatest { bankListValue ->
                CoroutineScope(Dispatchers.Main).launch {

                    if (bankListValue.isLoading) {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.bankRecycler.visibility = View.GONE
                        binding.myAccountsText.visibility = View.GONE

                    } else {
                        if (bankListValue.error.isNotBlank()) {
                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(
                                this@AccountListActivity,
                                bankListValue.error,
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("INJUSTE", tempBankList.toString())
                        } else {

                            binding.myAccountsText.visibility = View.VISIBLE
                            binding.progressCircular.visibility = View.GONE
                            tempBankList.addAll(bankListValue.banks)
                            bankAdapter.setData(tempBankList as ArrayList<Bank>)
                            Log.d("JUSTE", tempBankList.toString())
                            Toast.makeText(this@AccountListActivity, "Test A", Toast.LENGTH_SHORT)
                                .show()

                            binding.myAccountsText.text = tempBankList[0].name

                        }
                    }
                }
            }
        }
    }
}
























