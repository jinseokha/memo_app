package com.devseok.memo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devseok.domain.model.DomainNotice
import com.devseok.domain.usecase.NoticeUseCase
import com.devseok.domain.utils.ErrorType
import com.devseok.domain.utils.RemoteErrorEmitter
import com.devseok.memo.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val noticeUseCase: NoticeUseCase
) : ViewModel(), RemoteErrorEmitter {

    private val _getNoticeEvent = SingleLiveEvent<Int>()
    val getNoticeEvent : LiveData<Int>
        get() = _getNoticeEvent

    var noticeList = arrayListOf<DomainNotice>()

    fun getNotice() = noticeUseCase.execute()
        .addOnSuccessListener { snapshot ->
            noticeList.clear()
            for (item in snapshot.documents) {
                item.toObject(DomainNotice::class.java).let {
                    noticeList.add(it!!)
                }
            }

            _getNoticeEvent.call()
        }
        .addOnFailureListener {
            it.message.toString()
        }


    override fun onError(msg: String) {

    }

    override fun onError(errorType: ErrorType) {

    }
}