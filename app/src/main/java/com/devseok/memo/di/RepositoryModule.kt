package com.devseok.memo.di

import com.devseok.data.repository.MainRepositoryImpl
import com.devseok.data.repository.SettingRepositoryImpl
import com.devseok.data.repository.remote.datasource.MainDataSource
import com.devseok.data.repository.remote.datasource.SettingDataSource
import com.devseok.domain.repository.MainRepository
import com.devseok.domain.repository.SettingRepository
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
class RepositoryModule {


    @Provides
    @Singleton
    fun provideSettingRepository (
        settingDataSource: SettingDataSource
    ) : SettingRepository {
        return SettingRepositoryImpl (
            settingDataSource
        )
    }
}