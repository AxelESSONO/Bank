package com.axel.bank.presentation.operation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.axel.bank.databinding.ActivityOperationBinding
import com.axel.bank.domain.model.Operation
import com.axel.bank.presentation.bank.view.AccountListActivity
import com.axel.bank.util.convertJsonListToListOperation

class OperationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOperationBinding
    private lateinit var operationLinearLayoutManager: LinearLayoutManager
    private lateinit var operationAdapter: OperationAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        operationLinearLayoutManager = LinearLayoutManager(this)
        operationLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        val operations :  List<Operation> = convertJsonListToListOperation("OPERATIONS")
        val amount = intent.getDoubleExtra("AMOUNT", 0.0)
        val title = intent.getStringExtra("TITLE")

        binding.comeBack.setOnClickListener {
            startActivity(
                Intent(this, AccountListActivity::class.java )
            )
        }
        operationAdapter = OperationAdapter(operations.sortedBy { it.title })
        binding.balanceText.text = "$amount euros"
        binding.accountTitle.text = title

        binding.operationRecycler.apply {
            layoutManager = operationLinearLayoutManager
            adapter = operationAdapter
        }
    }
}