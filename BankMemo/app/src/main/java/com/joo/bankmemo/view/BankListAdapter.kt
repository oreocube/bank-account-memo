package com.joo.bankmemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joo.bankmemo.R
import com.joo.bankmemo.databinding.RvBankItemBinding
import com.joo.bankmemo.model.Bank

class BankListAdapter : RecyclerView.Adapter<BankListAdapter.BankListVH>() {

    private lateinit var clickListener : ClickListener
    private var bankList = emptyList<Bank>()

    inner class BankListVH(private val binding : RvBankItemBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(bank: Bank) {
            if(bank.symbol!=null) {
                binding.bankImageView.setImageResource(bank.symbol)
            } else {
                binding.bankImageView.setImageResource(R.drawable.ic_launcher_foreground)
            }
            binding.apply {
                bankItem = bank
            }

        }
        override fun onClick(v: View?) {
            if (v != null) {
                clickListener.onItemClicked(adapterPosition, v)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankListVH {
        return BankListVH(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.rv_bank_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: BankListVH, position: Int) {
        holder.bind(bankList[position])
    }

    override fun getItemCount(): Int {
        return bankList.size
    }

    fun setItem(items : List<Bank>) {
        if(!items.isNullOrEmpty()) {
            bankList = items
        }
    }

    interface ClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun setOnItemClickListener (clickListener: ClickListener) {
        this.clickListener = clickListener
    }
}