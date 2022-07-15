package com.devseok.data.repository

import com.devseok.data.repository.remote.datasource.MainDataSource
import com.devseok.data.repository.remote.datasource.SettingDataSource
import com.devseok.domain.repository.MainRepository
import com.devseok.domain.repository.SettingRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-13
 * @desc
 */
class SettingRepositoryImpl  @Inject constructor(
    private val mainDataSource: SettingDataSource
) : SettingRepository {
    override fun checkAppVersion(): Task<DataSnapshot> {
        return mainDataSource.checkAppVersion()
    }
}