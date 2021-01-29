package com.joo.bankmemo.model

import com.joo.bankmemo.R

fun bankList() : List<Bank> {
    return listOf(
        Bank(
            title = "국민은행",
            symbol = R.drawable.ic_kb
        ),
        Bank(
            title = "농협은행",
            symbol = R.drawable.ic_nh
        ),
        Bank(
            title = "신한은행",
            symbol = R.drawable.ic_shinhan
        ),
        Bank(
            title = "카카오뱅크",
            symbol = R.drawable.ic_kakao
        ),
        Bank(
            title = "기업은행",
            symbol = R.drawable.ic_ibk
        )
    )
}