package com.devseok.memo.view

import android.view.MenuItem
import androidx.activity.viewModels
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityEditBinding
import com.devseok.memo.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class EditActivity : BaseActivity<ActivityEditBinding>(R.layout.activity_edit) {
    private val editViewModel by viewModels<EditViewModel>()

    override fun init() {
        binding.activity = this

        initListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            withFinish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        withFinish()
    }

    private fun withFinish() {

        if (binding.editText.text.toString().trim().isEmpty().not()) {

            val memo = Memo(id = null, memo = binding.editText.text.toString(), editMode = false, secretMode = false, secretPassWord = "", convertTimestampToDate(System.currentTimeMillis()))
            editViewModel.insertMemo(memo)
            finish()
        } else {
            finish()
        }
    }

    private fun initListener() {
        binding.imageViewBack.setOnClickListener {
            withFinish()
        }
    }

    fun convertTimestampToDate(timestamp: Long) : String {
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분")
        val date = sdf.format(timestamp)

        return date
    }

}