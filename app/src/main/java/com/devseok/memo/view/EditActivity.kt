package com.devseok.memo.view

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Color.red
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityEditBinding
import com.devseok.memo.viewmodel.EditViewModel
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class EditActivity : BaseActivity<ActivityEditBinding>(R.layout.activity_edit) {
    private val editViewModel by viewModels<EditViewModel>()

    var fiXcolor : String = "#756E6E"

    override fun init() {
        binding.activity = this

        initListener()
        initObserver()
        adb()
        selectColor(fiXcolor)
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

            val memo = Memo(id = null, memo = binding.editText.text.toString(), editMode = false,
                secretMode = false, secretPassWord = "", secretEnabled = false,
                color = fiXcolor, mode = false, convertTimestampToDate(System.currentTimeMillis()))
            editViewModel.insertMemo(memo)
        } else {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }
    }

    private fun adb() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.d("test", "" + adError)
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    private fun initListener() {
        binding.imageViewBack.setOnClickListener {
            withFinish()
        }

        binding.image.setOnClickListener {
            ColorPickerDialog
                .Builder(this)
                .setTitle("설정")
                .setColorShape(ColorShape.SQAURE)
                .setDefaultColor(parseColor("#ffffff"))
                .setColorListener { color, colorHex ->
                    Log.d("test", "" + color + colorHex)
                    fiXcolor = colorHex
                    selectColor(colorHex)
                }
                .show()
        }

        binding.ivLock.setOnClickListener {
            if (binding.editText.text.toString().trim().isEmpty().not()) {
                val layoutInflater = LayoutInflater.from(this)
                val view: View = layoutInflater.inflate(R.layout.dialog_secret, null)

                val alertDialog = AlertDialog.Builder(this)
                    .setView(view)
                    .create()

                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val buttonCancel = view.findViewById<View>(R.id.cancel) as AppCompatButton
                val buttonConfirm = view.findViewById<View>(R.id.ok) as AppCompatButton
                val editTextValue = view.findViewById<View>(R.id.editTextValue) as AppCompatEditText

                alertDialog.show()

                buttonCancel.setOnClickListener { alertDialog.dismiss() }

                buttonConfirm.setOnClickListener {
                    if (editTextValue.text.toString().trim().isEmpty().not()) {
                        val memo = Memo(id = null, memo = binding.editText.text.toString(), editMode = false,
                            secretMode = true, secretPassWord = editTextValue.text.toString(), secretEnabled = true,
                            color = fiXcolor, mode = false, convertTimestampToDate(System.currentTimeMillis()))
                        editViewModel.insertMemo(memo)
                    }

                    alertDialog.dismiss()
                }
            } else {
                Toast.makeText(this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initObserver() {
        editViewModel.isInsertMemo.observe(this, {
            Toast.makeText(this, "메모를 저장했습니다.", Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        })
    }

    fun convertTimestampToDate(timestamp: Long) : String {
        val sdf = SimpleDateFormat("yyyy.MM.dd.(EE) a hh:mm")
        val date = sdf.format(timestamp)

        return date
    }

    fun selectColor(colorHex : String) {
        val drawable_color = ContextCompat.getDrawable(this, R.drawable.share_circle) as GradientDrawable?
        //drawable_color!!.setColor(ContextCompat.getColor(this, R.color.gray))
        drawable_color!!.setColor(Color.parseColor(colorHex))

        binding.image.setImageDrawable(drawable_color)
    }

}