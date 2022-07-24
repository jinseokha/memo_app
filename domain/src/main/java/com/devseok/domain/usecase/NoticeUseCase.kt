package com.devseok.domain.usecase

import com.devseok.domain.repository.NoticeRepository
import javax.inject.Inject

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
class NoticeUseCase @Inject constructor(
    private val noticeRepository : NoticeRepository
) {
    fun execute() = noticeRepository.getNotice()
}