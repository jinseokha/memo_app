package com.devseok.domain.usecase

import com.devseok.domain.repository.MainRepository
import com.devseok.domain.repository.SettingRepository
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-13
 * @desc
 */
class CheckAppVersionUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {
    fun execute() = settingRepository.checkAppVersion()
}