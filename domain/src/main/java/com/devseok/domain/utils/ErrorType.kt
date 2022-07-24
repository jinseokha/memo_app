package com.devseok.domain.utils

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-06-16
 * @desc
 */
enum class ErrorType {
    // 네트워크 문제
    NETWORK,

    // 요청 시간 초과
    TIMEOUT,

    // 세션 만료
    SESSION_EXPIRED,

    // 알 수 없는 다른 문제
    UNKNOWN
}