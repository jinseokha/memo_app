package com.devseok.memo.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
object NoticeBindingAdapter {

    @JvmStatic
    @BindingAdapter("set_title")
    fun setTitle(text : TextView, title : String) {
        text.text = title
    }
}