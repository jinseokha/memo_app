package com.devseok.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
import androidx.activity.viewModels
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivitySettingBinding
import com.devseok.memo.viewmodel.SettingViewModel
import com.devseok.memo.widget.utils.ThemeDarkManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    private val settingViewModel by viewModels<SettingViewModel>()

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

        /*if (binding.editText.text.toString().trim().isEmpty().not()) {

            val memo = Memo(id = null, memo = binding.editText.text.toString(), editMode = false,
                secretMode = false, secretPassWord = "", secretEnabled = false,
                color = fiXcolor, mode = false, convertTimestampToDate(System.currentTimeMillis()))
            editViewModel.insertMemo(memo)
        } else {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }*/
    }


    fun darkModeChange(buttonView: CompoundButton, isChecked: Boolean) {

        if (isChecked) {
            binding.switchDark.isChecked = true
            ThemeDarkManager.applyTheme(ThemeDarkManager.ThemeMode.DARK)
        } else {
            binding.switchDark.isChecked = false
            ThemeDarkManager.applyTheme(ThemeDarkManager.ThemeMode.LIGHT)
        }
    }
}