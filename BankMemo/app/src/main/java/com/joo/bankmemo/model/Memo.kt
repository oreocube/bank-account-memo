package com.joo.bankmemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joo.bankmemo.R

@Entity(tableName = "memo_table")
data class Memo(

    @ColumnInfo(name = "bank_id") // 은행 id
    val bank_id: Int,

    @ColumnInfo(name = "bank") // 은행명
    val bank: String,

    @ColumnInfo(name = "account_number") // 계좌번호
    val account_number: String,

    @ColumnInfo(name = "ba_nickname") // 계좌 별칭
    val ba_nickname: String,

    @ColumnInfo(name = "account_holder") // 예금주
    val account_holder: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}