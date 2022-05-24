package com.devseok.memo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.adapter.SearchAdapter
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivitySearchBinding
import com.devseok.memo.viewmodel.SearchViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val memoEditViewModel by viewModels<SearchViewModel>()

    override fun init() {
        binding.activity = this

        initListener()
        initObserver()
        adb()
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

        binding.ivDelete.setOnClickListener {
            binding.edtSearch.setText("")
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                var textLength : Int = binding.edtSearch.text!!.length

                if (textLength > 0)
                    memoEditViewModel.getMemo(binding.edtSearch.text.toString())

            }

            override fun afterTextChanged(edit: Editable?) {

            }
        })

        binding.edtSearch.setOnEditorActionListener { view, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val intent = Intent(baseContext, SearchResultActivity::class.java)
                    intent.putExtra("memo", binding.edtSearch.text.toString())
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
                } else -> {

                }
            }
            false
        }
    }

    private fun initObserver() {

        memoEditViewModel.isUpdateMemo.observe(this, {
            it.observe(this, Observer {
                var memoList = it.toMutableList()
                initRecyclerView(memoList)
            })
        })
    }

    private fun initRecyclerView(memo : MutableList<Memo>) {
        var searchAdapter = SearchAdapter(this, memo, memoEditViewModel)
        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }
}