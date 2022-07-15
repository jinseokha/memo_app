package com.devseok.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityOpenLicenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpenLicenseActivity : BaseActivity<ActivityOpenLicenseBinding>(R.layout.activity_open_license) {

    override fun init() {
        binding.activity = this
    }
}