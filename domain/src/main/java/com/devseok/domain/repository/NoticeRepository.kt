package com.devseok.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.QuerySnapshot

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
interface NoticeRepository {
    // 공지사항 내역 받아오기
    fun getNotice() : Task<QuerySnapshot>
}