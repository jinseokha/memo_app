package com.devseok.memo.widget.extension

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-10
 * @desc
 */
fun AppCompatActivity.startActivityWith(context: Context, activity: Class<*>) {
    startActivity(Intent(context, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
}