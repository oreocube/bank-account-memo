package com.joo.bankmemo.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.joo.bankmemo.R
import com.joo.bankmemo.databinding.ActivityAddMemoBinding
import com.joo.bankmemo.model.Memo
import com.joo.bankmemo.viewmodel.BankSelectionViewModel
import com.joo.bankmemo.viewmodel.MainViewModel

class AddMemoActivity : AppCompatActivity() {

    private val bankSelectVM : BankSelectionViewModel by viewModels()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddMemoBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_add_memo
        )

        binding.selectBankBtn.setOnClickListener{
            val bottomSheet = BottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        var bankID = -1;
        bankSelectVM.selectedBankID.observe(this, Observer {
            bankID = it
        })
        bankSelectVM.selectedBankName.observe(this, Observer{
            binding.selectBankBtn.text = it.toString()
        })

        binding.addButton.setOnClickListener {
            when {
                binding.selectBankBtn.text == "은행 선택" -> {
                    Toast.makeText(applicationContext, "은행을 선택해주세요.", Toast.LENGTH_SHORT).show()
                }
                binding.baNickname.text.isNullOrBlank() -> {
                    binding.baNickname.error = "계좌 별칭을 입력해주세요. (ex. 월급통장)"
                }
//                binding.accountNumberEditText.text?.length < 8 -> {
//                    binding.accountNumberEditText.error = "계좌번호를 입력해주세요."
//                }
                else -> {
                    viewModel.insert(
                        Memo(
                            bankID,
                            binding.selectBankBtn.text.toString(),
                            binding.accountNumberEditText.text.toString(),
                            binding.baNickname.text.toString(),
                            binding.accountHolderEditText.text.toString()
                        )
                    )
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }

}