package com.devseok.memo.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.devseok.memo.widget.presistence.Preferences
import com.devseok.memo.widget.utils.CustomBar
import com.devseok.memo.widget.utils.ThemeDarkManager

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-03
 * @desc
 */
abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {
    protected lateinit var binding: T
    private var waitTime = 0L
    lateinit var prefs : Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        prefs = Preferences(this)
        CustomBar.setStatusBarTransparent(this)
        init()
        darkMode()
    }

    fun darkMode() {
        if (prefs.isDarkModeEnabled)
            ThemeDarkManager.applyTheme(ThemeDarkManager.ThemeMode.DARK)
        else
            ThemeDarkManager.applyTheme(ThemeDarkManager.ThemeMode.LIGHT)
    }

    abstract fun init()

    protected fun shortShowToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    protected fun longShowToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}