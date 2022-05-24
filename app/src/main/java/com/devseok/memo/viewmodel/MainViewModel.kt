package com.devseok.memo.viewmodel

import androidx.lifecycle.LiveData
import com.devseok.data.model.Memo
import com.devseok.data.usecase.MemoRepoUseCase
import com.devseok.memo.base.BaseViewModel
import com.devseok.memo.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-03
 * @desc
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val memoRepoUseCase: MemoRepoUseCase
) : BaseViewModel() {

    val isGetAllMemo = SingleLiveEvent<LiveData<MutableList<Memo>>>()


    fun getMemo() {
        CoroutineScope(Dispatchers.IO).launch {
            memoRepoUseCase.getAllMemo().let {
                isGetAllMemo.postValue(it)
            }
        }
    }
}