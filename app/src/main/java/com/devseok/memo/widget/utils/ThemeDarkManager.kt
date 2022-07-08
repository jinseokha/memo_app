package com.devseok.memo.widget.utils

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-07-07
 * @desc
 */
object ThemeDarkManager {
    enum class ThemeMode { LIGHT, DARK, DEFAULT }

    fun applyTheme(themeMode : ThemeMode) {
        when (themeMode) {
            ThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }

}