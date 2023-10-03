package com.axel.bank.presentation.bank.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axel.bank.domain.use_case.BankListUseCase
import com.axel.bank.presentation.bank.BankState
import com.axel.bank.util.BankResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(
    private val bankListUseCase: BankListUseCase
) : ViewModel() {
    private val bankValue = MutableStateFlow(BankState())
    var _bankValue : StateFlow<BankState> = bankValue

    fun getAllBank() = viewModelScope.launch(Dispatchers.IO) {
        bankListUseCase().collect{
            when(it){
                is BankResponseState.Success -> {
                    bankValue.value = BankState(banks = it.data?: emptyList())
                }

                is BankResponseState.Loading -> {
                    bankValue.value = BankState(isLoading = true)
                }

                is BankResponseState.Error -> {
                    bankValue.value = BankState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}








































































