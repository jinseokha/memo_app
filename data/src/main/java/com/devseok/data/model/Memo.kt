package com.devseok.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-03
 * @desc ROOM DB Class
 */
@Entity
data class Memo (
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var memo : String,
    var editMode : Boolean,
    var secretMode : Boolean, // secret Mode
    var secretPassWord : String,
    var update_time : String
) {

}