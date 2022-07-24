package com.devseok.domain.model

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
data class DomainNotice(
    val seq : String,
    val date : String,
    val title : String,
    val content : String
) {
    constructor() : this("error","error","error","error")
}

