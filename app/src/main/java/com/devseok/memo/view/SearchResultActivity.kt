package com.devseok.memo.view

import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.adapter.SearchResultAdapter
import com.devseok.memo.adapter.SearchStaggeredAdapter
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
        if (prefs.isListVerticalMode) {
            if (memo.size == 0) {
                binding.recyclerView.visibility = View.GONE
                binding.tvMemo.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.tvMemo.visibility = View.GONE

                var searchResultAdapter = SearchResultAdapter(this, memo, searchResultViewModel)
                binding.recyclerView.adapter = searchResultAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
            }
        } else {
            if (memo.size == 0) {
                binding.recyclerView.visibility = View.GONE
                binding.tvMemo.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.tvMemo.visibility = View.GONE

                var searchStaggeredAdapter = SearchStaggeredAdapter(this, memo, searchResultViewModel)
                binding.recyclerView.adapter = searchStaggeredAdapter
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            }
        }
    }
}