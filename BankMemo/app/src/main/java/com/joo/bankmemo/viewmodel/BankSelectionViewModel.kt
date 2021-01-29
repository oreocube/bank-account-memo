package com.joo.bankmemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BankSelectionViewModel : ViewModel(){

    val selectedBankID = MutableLiveData<Int>()
    val selectedBankName = MutableLiveData<String>()

    fun setSelectedBankID(id : Int) {
        selectedBankID.value = id

        when(id) {
            0 -> selectedBankName.value = "국민은행"
            1 -> selectedBankName.value = "농협은행"
            2 -> selectedBankName.value = "신한은행"
            3 -> selectedBankName.value = "카카오뱅크"
            4 -> selectedBankName.value = "기업은행"
        }
    }
}