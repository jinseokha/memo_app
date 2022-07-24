package com.devseok.domain.utils

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-06-16
 * @desc
 */
interface RemoteErrorEmitter {
    fun onError(msg : String)
    fun onError(errorType : ErrorType)
}