package com.devseok.data.usecase

import com.devseok.data.dao.MemoDao
import com.devseok.data.model.Memo
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-10
 * @desc
 */
class MemoRepoUseCase @Inject constructor(
    private val memoDao : MemoDao
) {

    fun getAllMemo() = memoDao.getAllMemo()

    fun getMemo(memo : String) = memoDao.getMemo(memo)

    suspend fun insertMemo(memo : Memo) = memoDao.insertMemo(memo)

    suspend fun deleteMemo(memo : Memo) = memoDao.deleteMemo(memo)

    suspend fun deleteMemoById(id: Long) = memoDao.deleteMemoById(id)

    suspend fun modifyMemo(memo: Memo) = memoDao.modifyMemo(memo)
}