package com.devseok.memo.view

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.activity.viewModels
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivitySettingBinding
import com.devseok.memo.viewmodel.SettingViewModel
import com.devseok.memo.widget.extension.startActivityWith
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    private val settingViewModel by viewModels<SettingViewModel>()

    private val appVersion = "1.0.0"
    private var isLatestUpdateCheck = false

    override fun init() {
        binding.activity = this

        appVersionChecking()
        initListener()
        getClick()
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

    private fun getClick() {
        if (prefs.isListVerticalMode) {
            binding.radioVertical.isChecked = true
            binding.radioGrid.isChecked = false
        } else {
            binding.radioVertical.isChecked = false
            binding.radioGrid.isChecked = true
        }
    }

    private fun initListener() {
        binding.imageViewBack.setOnClickListener {
            withFinish()
        }
    }

    private fun withFinish() {
        finish()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }

    private fun appVersionChecking() {
        settingViewModel.checkAppVersion()
            .addOnSuccessListener {
                if (appVersion == it.value) {
                    binding.tvVersion.text = "최신 버전"
                    isLatestUpdateCheck = true
                } else {
                    binding.tvVersion.text = "추가 업데이트 버전이 있습니다."
                    isLatestUpdateCheck = false
                }
            }
            .addOnFailureListener {
                shortShowToast("오류가 발생했습니다. 오류코드 - ${it.message}")
                isLatestUpdateCheck = false
            }
    }

    fun darkModeChange(buttonView: CompoundButton, isChecked: Boolean) {

        if (isChecked) {
            binding.switchDark.isChecked = true
            prefs.isDarkModeEnabled = true
        } else {
            binding.switchDark.isChecked = false
            prefs.isDarkModeEnabled = false
        }
        darkMode()
    }

    fun contact(view : View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/ZqzNtNN3RUJEG2fU7"))
        startActivity(intent)
    }

    fun updateHistory(view : View) {
        startActivityWith(baseContext, NoticeActivity::class.java)
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

    fun openLicenseActivity(view: View) {
        startActivityWith(baseContext, OpenLicenseActivity::class.java)
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

    fun playStoreSite(view : View) {
        if (isLatestUpdateCheck) {
            longShowToast("최신 업데이트 적용된 버전입니다.")
        } else {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + packageName))
            )
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        }
    }

    fun clickVertical(view : View) {
        when(view.id) {
            R.id.radioVertical -> prefs.isListVerticalMode = true
            R.id.radioGrid -> prefs.isListVerticalMode = false
        }
    }

}