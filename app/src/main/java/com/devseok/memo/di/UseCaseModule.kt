package com.devseok.memo.di

import com.devseok.domain.repository.MainRepository
import com.devseok.domain.repository.SettingRepository
import com.devseok.domain.usecase.CheckAppVersionUseCase
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
class UseCaseModule {
    @Provides
    @Singleton
    fun provideCheckAppVersionUseCase(repository: SettingRepository)
            = CheckAppVersionUseCase(repository)
}