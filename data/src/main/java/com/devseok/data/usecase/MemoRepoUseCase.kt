package com.devseok.data.usecase

import com.devseok.data.dao.MemoDao
import com.devseok.data.model.Memo
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-10
 * @desc
 */
class MemoRepoUseCase @Inject constructor(
    private val memoDao : MemoDao
) {

    fun getAllMemo() = memoDao.getAllMemo()

    suspend fun insertMemo(memo : Memo) = memoDao.insertMemo(memo)

    suspend fun deleteMemo(memo : Memo) = memoDao.deleteMemo(memo)

    suspend fun deleteMemoById(id: Long) = memoDao.deleteMemoById(id)

    suspend fun modifyMemo(id : Long, memo : String) = memoDao.modifyMemo(id, memo)
}