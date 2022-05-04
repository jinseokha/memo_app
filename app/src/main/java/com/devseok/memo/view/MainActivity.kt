package com.devseok.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityMainBinding
import com.devseok.memo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {
        binding.activity = this

    }
}