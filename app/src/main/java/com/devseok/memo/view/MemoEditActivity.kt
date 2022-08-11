package com.devseok.memo.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
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
import com.devseok.memo.databinding.ActivityMemoEditBinding
import com.devseok.memo.viewmodel.MemoEditViewModel
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MemoEditActivity : BaseActivity<ActivityMemoEditBinding>(R.layout.activity_memo_edit) {
    private val memoEditViewModel by viewModels<MemoEditViewModel>()

    lateinit var memo : Memo

    var fiXcolor : String = "#756E6E"

    override fun init() {
        binding.activity = this

        memo = intent.getSerializableExtra("memo") as Memo
        binding.editText.setText(memo.memo)

        var inputCount = binding.editText.text.toString()
        binding.edtCount.text = inputCount.length.toString() + ""

        secretMode()
        initListener()
        initObserver()
        lockicon()

        fiXcolor = memo.color
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

    private fun secretMode() {
        if (memo.secretMode) {
            binding.layoutLock.visibility = View.VISIBLE
            binding.edtCount.visibility = View.GONE
            binding.editText.visibility = View.GONE
        } else {
            binding.layoutLock.visibility = View.GONE
            binding.edtCount.visibility = View.VISIBLE
            binding.editText.visibility = View.VISIBLE
        }
    }

    private fun lockicon() {
        if (memo.secretMode)
            binding.ivLock.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_lock_open_24))
         else
            binding.ivLock.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_lock_24))
    }

    private fun passwordDialog() {
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
                if(memo.secretPassWord.equals(editTextValue.text.toString())) {
                    binding.layoutLock.visibility = View.GONE
                    binding.edtCount.visibility = View.VISIBLE
                    binding.editText.visibility = View.VISIBLE

                    memo.secretEnabled = false

                    memoEditViewModel.update(memo)
                } else {
                    Toast.makeText(this,"비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            alertDialog.dismiss()
        }
    }

    private fun withFinish() {
        var secretEnabled = false
        if (memo.secretPassWord.equals("") == false)
            secretEnabled = true

        if (binding.editText.text.toString().trim().isEmpty().not()) {
            val memo = Memo(id = memo.id, memo = binding.editText.text.toString(), editMode = false,
                secretMode = memo.secretMode, secretPassWord = memo.secretPassWord, secretEnabled = secretEnabled,
                color = fiXcolor, mode = false, convertTimestampToDate(System.currentTimeMillis()))
            memoEditViewModel.updateMemo(memo)
        } else {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }
    }

    private fun initListener() {
        binding.imageViewBack.setOnClickListener {
            withFinish()
        }

        binding.ivDelete.setOnClickListener {
            if (memo.secretEnabled) {
                val layoutInflater = LayoutInflater.from(this)
                val view : View = layoutInflater.inflate(R.layout.dialog_notice, null)

                val alertDialog = AlertDialog.Builder(this)
                    .setView(view)
                    .create()

                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val buttonCancel = view.findViewById<View>(R.id.cancel) as AppCompatButton
                val buttonConfirm = view.findViewById<View>(R.id.ok) as AppCompatButton

                alertDialog.show()

                buttonCancel.setOnClickListener { alertDialog.dismiss() }

                buttonConfirm.setOnClickListener {
                    memoEditViewModel.deleteMemo(memo)

                    alertDialog.dismiss()
                }

            } else {
                memoEditViewModel.deleteMemo(memo)
            }

        }

        binding.layoutLock.setOnClickListener {
            passwordDialog()
        }

        binding.ivShare.setOnClickListener {

            if (memo.secretEnabled) {
                Toast.makeText(this, "비밀메모 잠금을 해제 후 공유가 가능합니다.", Toast.LENGTH_SHORT).show()
            } else {
                val sendIntent : Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, memo.memo)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

        binding.ivLock.setOnClickListener {

            if (memo.secretMode == false) {
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
                        val memo = Memo(id = memo.id, memo = binding.editText.text.toString(), editMode = false,
                            secretMode = true, secretPassWord = editTextValue.text.toString(), secretEnabled = true,
                            color = memo.color, mode = false, convertTimestampToDate(System.currentTimeMillis()))
                        memoEditViewModel.updateMemo(memo)
                    }

                    alertDialog.dismiss()
                }
            } else {
                if (memo.secretEnabled) {
                    Toast.makeText(this, "비밀모드를 먼저 해제해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    val layoutInflater = LayoutInflater.from(this)
                    val view: View = layoutInflater.inflate(R.layout.dialog_secret_off, null)

                    val alertDialog = AlertDialog.Builder(this)
                        .setView(view)
                        .create()

                    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    val buttonCancel = view.findViewById<View>(R.id.cancel) as AppCompatButton
                    val buttonConfirm = view.findViewById<View>(R.id.ok) as AppCompatButton

                    alertDialog.show()

                    buttonCancel.setOnClickListener { alertDialog.dismiss() }

                    buttonConfirm.setOnClickListener {
                        val memo = Memo(id = memo.id, memo = binding.editText.text.toString(), editMode = false,
                            secretMode = false, secretPassWord = "", secretEnabled = false,
                            color = memo.color, mode = false, convertTimestampToDate(System.currentTimeMillis()))
                        memoEditViewModel.updateMemo(memo)

                        alertDialog.dismiss()
                    }
                }
            }

        }

        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                var inputCount = binding.editText.text.toString()
                binding.edtCount.text = inputCount.length.toString() + ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var inputCount = binding.editText.text.toString()
                binding.edtCount.text = inputCount.length.toString() + ""
            }

            override fun afterTextChanged(s: Editable?) {
                var inputCount = binding.editText.text.toString()
                binding.edtCount.text = inputCount.length.toString() + ""
            }
        })

        binding.image.setOnClickListener {
            ColorPickerDialog
                .Builder(this)
                .setTitle("설정")
                .setColorShape(ColorShape.SQAURE)
                .setDefaultColor(Color.parseColor("#ffffff"))
                .setColorListener { color, colorHex ->
                    Log.d("test", "" + color + colorHex)
                    fiXcolor = colorHex
                    selectColor(colorHex)
                }
                .show()
        }
    }

    private fun initObserver() {
        memoEditViewModel.isUpdateMemo.observe(this, {
            Toast.makeText(this, "메모를 저장했습니다.", Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        })

        memoEditViewModel.isDeleteMemo.observe(this, {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        })

        memoEditViewModel.isUpdate.observe(this, {
            memo = it
        })
    }

    fun convertTimestampToDate(timestamp: Long) : String {
        val sdf = SimpleDateFormat("yyyy.MM.dd.(EE) a hh:mm")
        val date = sdf.format(timestamp)

        return date
    }

    fun selectColor(colorHex : String) {
        val drawable_color = ContextCompat.getDrawable(this, R.drawable.share_circle) as GradientDrawable?
        drawable_color!!.setColor(Color.parseColor(colorHex))

        binding.image.setImageDrawable(drawable_color)
    }
}