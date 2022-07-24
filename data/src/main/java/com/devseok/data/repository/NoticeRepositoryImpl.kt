package com.devseok.data.repository

import com.devseok.data.repository.remote.datasource.NoticeDataSource
import com.devseok.domain.repository.NoticeRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource : NoticeDataSource
) : NoticeRepository {
    override fun getNotice(): Task<QuerySnapshot> {
        return noticeDataSource.getNotice()
    }
}