package com.devseok.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devseok.data.model.Memo

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-03
 * @desc
 */
@Dao
interface MemoDao {

    // return Long Get Id
    @Insert
    suspend fun insertMemo(memo : Memo) : Long

    @Delete
    suspend fun deleteMemo(memo : Memo)

    @Query("DELETE FROM Memo Where id = :id")
    suspend fun deleteMemoById(id : Long)

    @Query("SELECT * FROM Memo")
    fun getAllMemo() : LiveData<MutableList<Memo>>

    @Query("UPDATE Memo SET memo = :memo WHERE id = :id")
    suspend fun modifyMemo(id: Long, memo: String)
}