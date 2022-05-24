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
 * @created 2022-05-19
 * @desc
 */
@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val memoRepoUseCase: MemoRepoUseCase
) : BaseViewModel() {

    val isGetMemo = SingleLiveEvent<LiveData<MutableList<Memo>>>()

    fun getMemo(memo : String) {
        CoroutineScope(Dispatchers.IO).launch {
            memoRepoUseCase.getMemo(memo).let {
                isGetMemo.postValue(it)
            }
        }
    }

}