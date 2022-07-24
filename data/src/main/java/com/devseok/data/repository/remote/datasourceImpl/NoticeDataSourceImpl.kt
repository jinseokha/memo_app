package com.devseok.data.repository.remote.datasourceImpl

import com.devseok.data.repository.remote.datasource.NoticeDataSource
import com.devseok.data.utils.BaseDataSource
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
class NoticeDataSourceImpl @Inject constructor(
    private val firebaseRTDB : FirebaseDatabase,
    private val fireStore : FirebaseFirestore
) : BaseDataSource(), NoticeDataSource {

    override fun getNotice(): Task<QuerySnapshot> {
        return fireStore.collection("notice").orderBy("seq", Query.Direction.DESCENDING).get()
    }
}