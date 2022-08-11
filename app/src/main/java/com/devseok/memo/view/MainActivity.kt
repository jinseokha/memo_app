package com.devseok.memo.view

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.adapter.MemoAdapter
import com.devseok.memo.adapter.MemoStaggeredAdapter
import com.devseok.memo.base.BaseActivity
import com.devseok.memo.databinding.ActivityMainBinding
import com.devseok.memo.viewmodel.MainViewModel
import com.devseok.memo.widget.extension.startActivityWith
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var memoAdapter: MemoAdapter
    private lateinit var memoStaggeredAdapter: MemoStaggeredAdapter

    private lateinit var memoList : MutableList<Memo>

    private var waitTime = 0L


    override fun init() {
        binding.activity = this

        MobileAds.initialize(this) { }

        initObserver()
        initListener()
        adb()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - waitTime >= 1500) {
            waitTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else
            finish()
    }

    override fun onPause() {
        binding.adView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        getMemo()
        binding.adView.resume()
    }

    override fun onDestroy() {
        binding.adView.destroy()
        super.onDestroy()
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
        mainViewModel.isGetAllMemo.observe(this, {
            it.observe(this, Observer {
                memoList = it.toMutableList()
                initRecyclerView()
            })
        })
    }

    private fun initRecyclerView() {
        if (prefs.isListVerticalMode) {
            if (memoList.size == 0) {
                binding.recyclerView.visibility = View.GONE
                binding.tvMemo.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.tvMemo.visibility = View.GONE

                memoAdapter = MemoAdapter(this, memoList, mainViewModel)
                binding.recyclerView.adapter = memoAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
            }
        } else {
            if (memoList.size == 0) {
                binding.recyclerView.visibility = View.GONE
                binding.tvMemo.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.tvMemo.visibility = View.GONE

                memoStaggeredAdapter = MemoStaggeredAdapter(this, memoList, mainViewModel)
                binding.recyclerView.adapter = memoStaggeredAdapter
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            }
        }
    }

    private fun initListener() {

        /*binding.1ivContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/ZqzNtNN3RUJEG2fU7"))
            startActivity(intent)
        }*/

        binding.ivSearch.setOnClickListener {
            startActivityWith(baseContext, SearchActivity::class.java)
        }
    }

    private fun getMemo() {
        mainViewModel.getMemo()
    }

    fun goEditActivity(view: View) {
        startActivityWith(baseContext, EditActivity::class.java)
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

    fun goSettingActivity(view: View) {
        startActivityWith(baseContext, SettingActivity::class.java)
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }


}