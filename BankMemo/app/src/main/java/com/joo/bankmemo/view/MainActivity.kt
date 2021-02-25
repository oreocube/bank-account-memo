package com.joo.bankmemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.widget.Filterable
import android.widget.SearchView
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.joo.bankmemo.R
import com.joo.bankmemo.databinding.ActivityMainBinding
import com.joo.bankmemo.model.Memo
import com.joo.bankmemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var mAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        mAdapter = MainAdapter()
        binding.memoRecyclerview.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.allMemo.observe(this, Observer { memo ->
            memo?.let { mAdapter.setMemo(it) }
        })

        binding.addButton.setOnClickListener {
            val addIntent = Intent(this, AddMemoActivity::class.java)
            startActivity(addIntent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (mAdapter as Filterable).filter.filter(newText)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}