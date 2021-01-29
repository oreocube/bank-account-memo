package com.joo.bankmemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.joo.bankmemo.R
import com.joo.bankmemo.model.Memo
import com.joo.bankmemo.model.MemoDatabase
import com.joo.bankmemo.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : Repository = Repository(
        MemoDatabase.getDatabase(application, viewModelScope)
    )
    var allMemo : LiveData<List<Memo>> = repository.allMemo

    fun insert(memo: Memo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(memo)
    }

    fun update(memo: Memo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(memo)
    }

    fun delete(memo: Memo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(memo)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }


}