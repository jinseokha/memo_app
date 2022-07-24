package com.devseok.memo.di

import com.devseok.data.repository.remote.datasource.NoticeDataSource
import com.devseok.data.repository.remote.datasource.SettingDataSource
import com.devseok.data.repository.remote.datasourceImpl.NoticeDataSourceImpl
import com.devseok.data.repository.remote.datasourceImpl.SettingDataSourceImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-13
 * @desc
 */
@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {


    @Provides
    @Singleton
    fun provideSettingDataSource(
        firebaseRTDB : FirebaseDatabase,
        firebaseStore : FirebaseFirestore
    ) : SettingDataSource {
        return SettingDataSourceImpl(
            firebaseRTDB, firebaseStore
        )
    }

    @Provides
    @Singleton
    fun provideNoticeDataSource(
        firebaseRTDB: FirebaseDatabase,
        firebaseStore: FirebaseFirestore
    ) : NoticeDataSource {
        return NoticeDataSourceImpl(
            firebaseRTDB, firebaseStore
        )
    }
}