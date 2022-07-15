package com.devseok.memo.widget.presistence

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-13
 * @desc
 */
class Preferences(context : Context) {

    var PREFS_NAME = "com.devseok.memo.sharepreferences"
    val prefs : SharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    var isDarkModeEnabled : Boolean
        get() = prefs.getBoolean("isDarkModeEnabled", false)
        set(value) {
            prefs.edit().putBoolean("isDarkModeEnabled", value).apply()
        }
}