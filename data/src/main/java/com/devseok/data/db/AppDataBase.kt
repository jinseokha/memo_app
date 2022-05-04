package com.devseok.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.devseok.data.dao.MemoDao
import com.devseok.data.model.Memo
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-03
 * @desc
 */
@Database(entities = [Memo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun memoDao() : MemoDao

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