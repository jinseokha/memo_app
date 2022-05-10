package com.devseok.data.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devseok.data.dao.MemoDao
import com.devseok.data.model.Memo
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.Executors

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-03
 * @desc
 */
@Database(entities = [Memo::class], version = 1, exportSchema = false)
@TypeConverters(AppDataBase.Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun memoDao() : MemoDao

    companion object {
        fun getInstance(context: Context): AppDataBase = Room
            .databaseBuilder(context, AppDataBase::class.java, "memoNoteApp.db")
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    /*Executors.newSingleThreadExecutor().execute {
                        runBlocking {
                            getInstance(context).memoDao().insertMemo()
                            getInstance(context).albumDao().addAlbum(Memo.DEFAULT_ALBUM)
                        }
                    }*/
                }
            })
            .build()
    }

    class Converters {
        @TypeConverter
        fun fromTimestamp(value : Long) : Date {
            return Date(value)
        }

        @TypeConverter
        fun dateToTimestamp(date : Date) : Long {
            return date.time
        }
    }
}