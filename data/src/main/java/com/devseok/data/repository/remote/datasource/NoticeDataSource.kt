package com.devseok.data.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.QuerySnapshot

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
interface NoticeDataSource {

    fun getNotice() : Task<QuerySnapshot>
}