package com.devseok.data.db

import android.content.Context
import androidx.room.Room
import com.devseok.data.dao.MemoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-10
 * @desc
 */
@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context : Context
    ) : AppDataBase = AppDataBase.getInstance(context)

    @Singleton
    @Provides
    fun provideMemoData(appDataBase: AppDataBase) : MemoDao = appDataBase.memoDao()
}