package com.devseok.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityUpdateRecoredBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateRecoredActivity : BaseActivity<ActivityUpdateRecoredBinding>(R.layout.activity_update_recored) {

    override fun init() {
        binding.activity = this
    }
}