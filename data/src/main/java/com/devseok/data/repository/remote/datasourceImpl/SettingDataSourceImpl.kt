package com.devseok.data.repository.remote.datasourceImpl

import com.devseok.data.repository.remote.datasource.MainDataSource
import com.devseok.data.repository.remote.datasource.SettingDataSource
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-13
 * @desc
 */
class SettingDataSourceImpl @Inject constructor(
    private val firebaseRTDB : FirebaseDatabase,
    private val fireStore : FirebaseFirestore
) : SettingDataSource {
    override fun checkAppVersion(): Task<DataSnapshot> {
        return firebaseRTDB.reference.child("version").get()
    }
}