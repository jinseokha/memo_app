package com.devseok.memo.view

import android.view.MenuItem
import androidx.activity.viewModels
import com.devseok.memo.R
import com.devseok.memo.adapter.NoticeRecyclerViewAdapter
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityNoticeBinding
import com.devseok.memo.viewmodel.NoticeViewModel
import com.devseok.memo.widget.extension.showVertical
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeActivity : BaseActivity<ActivityNoticeBinding>(R.layout.activity_notice) {

    private val noticeViewModel by viewModels<NoticeViewModel>()

    override fun init() {
        binding.activity = this
        observeViewModel()
        noticeViewModel.getNotice()
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
        finish()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }

    private fun observeViewModel() {
        noticeViewModel.getNoticeEvent.observe(this) {
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.noticeRecyclerView.adapter = NoticeRecyclerViewAdapter(noticeViewModel)
        binding.noticeRecyclerView.showVertical(context = baseContext)
    }

}