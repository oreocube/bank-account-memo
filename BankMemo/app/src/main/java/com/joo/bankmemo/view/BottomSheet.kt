package com.joo.bankmemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joo.bankmemo.R
import com.joo.bankmemo.databinding.BottomSheetBinding
import com.joo.bankmemo.model.bankList
import com.joo.bankmemo.viewmodel.BankSelectionViewModel

class BottomSheet : BottomSheetDialogFragment() {

    private val bankSelectVM : BankSelectionViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet, container, false)

        val mAdapter = BankListAdapter()
        val list = bankList()

        mAdapter.setItem(list)
        mAdapter.setOnItemClickListener(object : BankListAdapter.ClickListener {
            override fun onItemClicked(position: Int, view: View) {
                bankSelectVM.setSelectedBankID(position)
                dismiss()
            }
        })

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        return view
    }
}