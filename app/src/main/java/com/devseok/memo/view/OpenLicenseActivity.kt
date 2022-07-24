package com.devseok.memo.view

import android.view.MenuItem
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityOpenLicenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpenLicenseActivity : BaseActivity<ActivityOpenLicenseBinding>(R.layout.activity_open_license) {

    override fun init() {
        binding.activity = this
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
}