package com.devseok.data.repository

import com.devseok.memo.di.App

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-03
 * @desc
 */
class MemoRepository {
    private val appDBInstance = App.appDatabaseInstance.memoDao()


}