package com.joo.bankmemo.model

import androidx.lifecycle.LiveData

class Repository(mDatabase : MemoDatabase) {

    private val memoDao = mDatabase.memoDao()
    val allMemo : LiveData<List<Memo>> = memoDao.getAllByBankID()

    companion object {
        private var sInstance : Repository? = null
        fun getInstance(database: MemoDatabase) : Repository {
            return sInstance
                ?: synchronized(this) {
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }

    suspend fun insert(memo: Memo) {
        memoDao.insert(memo)
    }

    suspend fun update(memo: Memo) {
        memoDao.update(memo)
    }

    suspend fun delete(memo: Memo) {
        memoDao.delete(memo)
    }

    suspend fun deleteAll() {
        memoDao.deleteAll()
    }
}