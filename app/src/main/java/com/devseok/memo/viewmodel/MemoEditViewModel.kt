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
 * @email seok270@gmail.com
 * @created 2022-05-11
 * @desc
 */
@HiltViewModel
class MemoEditViewModel @Inject constructor(
    private val memoRepoUseCase: MemoRepoUseCase
) : BaseViewModel() {

    val isUpdateMemo = SingleLiveEvent<Boolean>()
    val isDeleteMemo = SingleLiveEvent<Boolean>()

    val isUpdate = SingleLiveEvent<Memo>()

    fun updateMemo(memo : Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoRepoUseCase.modifyMemo(memo).let {
                isUpdateMemo.postValue(true)
            }
        }
    }

    fun update(memo : Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoRepoUseCase.modifyMemo(memo).let {
                isUpdate.postValue(memo)
            }
        }
    }

    fun deleteMemo(memo : Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoRepoUseCase.deleteMemo(memo).let {
                isDeleteMemo.postValue(true)
            }
        }
    }
}