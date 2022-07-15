package com.devseok.memo.viewmodel

import com.devseok.data.usecase.MemoRepoUseCase
import com.devseok.domain.usecase.CheckAppVersionUseCase
import com.devseok.memo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-04
 * @desc
 */
@HiltViewModel
class SettingViewModel @Inject constructor(
    private val memoRepoUseCase: MemoRepoUseCase,
    private val checkAppVersionUseCase : CheckAppVersionUseCase
) : BaseViewModel() {

    fun checkAppVersion() = checkAppVersionUseCase.execute()

}