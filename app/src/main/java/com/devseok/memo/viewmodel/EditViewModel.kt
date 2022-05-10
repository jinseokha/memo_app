package com.devseok.memo.viewmodel

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
 * @email seok270@dahami.com
 * @created 2022-05-10
 * @desc
 */
@HiltViewModel
class EditViewModel @Inject constructor(
    private val memoRepoUseCase: MemoRepoUseCase
) : BaseViewModel() {

    val isInsertMemo = SingleLiveEvent<Long>()

    fun insertMemo(memo : Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoRepoUseCase.insertMemo(memo).let {
                isInsertMemo.postValue(it)
            }
        }
    }

}