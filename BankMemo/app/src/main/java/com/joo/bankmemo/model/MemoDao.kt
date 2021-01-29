package com.joo.bankmemo.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo_table ORDER BY id")
    fun getAllByID() : LiveData<List<Memo>>

    @Query("SELECT * FROM memo_table ORDER BY bank_id")
    fun getAllByBankID() : LiveData<List<Memo>>

    @Insert
    suspend fun insert(memo: Memo)

    @Update
    suspend fun update(memo: Memo)

    @Delete
    suspend fun delete(memo: Memo)

    @Query("DELETE FROM memo_table")
    fun deleteAll()

    // insert, delete 메소드는 코루틴 스코프 또는 스레드에서 사용해야 함
    // 코루틴 스코프에서 사용하기 위해 suspend

    // suspend 함수는 현재 코루틴 실행을 일시중지하고 모든 로컬 변수를 저장
    // suspend 함수는 다른 suspend 함수에서 호출하거나,
    // 코루틴 빌더(예: launch)를 사용하여 새 코루틴을 시작하는 방법으로만 호출할 수 있음

}