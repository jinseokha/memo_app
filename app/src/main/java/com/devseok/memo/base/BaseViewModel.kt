package com.devseok.memo.base

import androidx.lifecycle.ViewModel
import com.devseok.memo.widget.utils.ScreenState
import com.devseok.memo.widget.utils.SingleLiveEvent

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-03
 * @desc
 */
abstract class BaseViewModel : ViewModel() {

    val mutableScreenState = SingleLiveEvent<ScreenState>()

}