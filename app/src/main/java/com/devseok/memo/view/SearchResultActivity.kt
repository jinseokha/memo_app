package com.devseok.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.adapter.SearchResultAdapter
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivitySearchResultBinding
import com.devseok.memo.viewmodel.SearchResultViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val searchResultViewModel by viewModels<SearchResultViewModel>()

    override fun init() {
        binding.activity = this

        var memo = intent.getStringExtra("memo")


        searchResultViewModel.getMemo(memo!!)

        initObserver()
        adb()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
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

    private fun initObserver() {
        searchResultViewModel.isGetMemo.observe(this, {
            it.observe(this, {
                var memoList = it.toMutableList()
                binding.tvResult.setText("검색결과 " + memoList.size + "개")
                initRecyclerView(memoList)
            })
        })
    }

    private fun initRecyclerView(memo : MutableList<Memo>) {
        var searchResultAdapter = SearchResultAdapter(this, memo, searchResultViewModel)
        binding.recyclerView.adapter = searchResultAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }
}