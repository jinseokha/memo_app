package com.devseok.memo.view

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.adapter.MemoAdapter
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityMainBinding
import com.devseok.memo.viewmodel.MainViewModel
import com.devseok.memo.widget.extension.startActivityWith
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var memoAdapter: MemoAdapter
    private lateinit var memoList : MutableList<Memo>

    private var waitTime = 0L

    override fun init() {
        binding.activity = this

        getMemo()
        initObserver()
        initListener()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - waitTime >= 1500) {
            waitTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else
            finish()
    }


    private fun initObserver() {
        mainViewModel.isGetAllMemo.observe(this, {
            it.observe(this, Observer {
                memoList = it.toMutableList()
                initRecyclerView()
            })
        })
    }

    private fun initRecyclerView() {
        memoAdapter = MemoAdapter(baseContext, memoList, mainViewModel)
        binding.recyclerView.adapter = memoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }

    private fun initListener() {
        binding.ivEdit.setOnClickListener {
            startActivityWith(baseContext, EditActivity::class.java)
        }

        binding.ivSearch.setOnClickListener {

        }
    }

    private fun getMemo() {
        mainViewModel.getMemo()
    }
}