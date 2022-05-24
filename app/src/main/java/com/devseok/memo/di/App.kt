package com.devseok.memo.di

import android.app.Application
import androidx.room.Room
import com.devseok.data.db.AppDataBase
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-03
 * @desc
 */
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var application : App
            private set

        lateinit var appDatabaseInstance : AppDataBase
            private set

        fun getInstance() : App = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        appDatabaseInstance = Room.databaseBuilder(
            application.applicationContext,
            AppDataBase::class.java, "memoNoteApp.db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}