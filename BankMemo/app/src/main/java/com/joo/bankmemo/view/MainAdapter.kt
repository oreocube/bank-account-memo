package com.joo.bankmemo.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joo.bankmemo.R
import com.joo.bankmemo.databinding.RvMemoItemBinding
import com.joo.bankmemo.model.Memo

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainVH>() {

    private lateinit var mContext: Context
    private var memo = emptyList<Memo>()

    inner class MainVH(private val binding : RvMemoItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo){
            when (memo.bank_id) {
                0 -> binding.bankSymbol.setImageResource(R.drawable.ic_kb)
                1 -> binding.bankSymbol.setImageResource(R.drawable.ic_nh)
                2 -> binding.bankSymbol.setImageResource(R.drawable.ic_shinhan)
                3 -> binding.bankSymbol.setImageResource(R.drawable.ic_kakao)
                4 -> binding.bankSymbol.setImageResource(R.drawable.ic_ibk)
            }
            binding.apply {
                memoItem = memo
            }
            binding.copyButton.setOnClickListener {
                copyAccountNumber(it, memo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {

        mContext = parent.context

        return MainVH(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.rv_memo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.bind(memo[position])
    }

    override fun getItemCount(): Int = memo.size

    fun setMemo(memo: List<Memo>) {
        this.memo = memo
        notifyDataSetChanged()
    }

    fun copyAccountNumber(view : View, memo: Memo) {
        val mClipboard: ClipboardManager =
            view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipText = "[" + memo.bank + "] " + memo.account_number
        val mClip = ClipData.newPlainText("label", clipText)
        mClipboard.setPrimaryClip(mClip)
        Toast.makeText(mContext, "$clipText 복사됨", Toast.LENGTH_SHORT).show()
    }
}