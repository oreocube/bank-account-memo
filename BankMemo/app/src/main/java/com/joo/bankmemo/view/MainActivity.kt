package com.joo.bankmemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.joo.bankmemo.R
import com.joo.bankmemo.databinding.ActivityMainBinding
import com.joo.bankmemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_MEMO_REQUEST = 1
    }
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )


        val mAdapter = MainAdapter()
        binding.memoRecyclerview. apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.allMemo.observe(this, Observer {
            memo -> memo?. let { mAdapter.setMemo(it) }
        })

        binding.addButton.setOnClickListener {
            val addIntent = Intent(this, AddMemoActivity::class.java)
            startActivityForResult(addIntent, ADD_MEMO_REQUEST)
        }


    }
}