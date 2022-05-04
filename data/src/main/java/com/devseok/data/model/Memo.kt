package com.devseok.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-03
 * @desc ROOM DB Class
 */
@Entity
class Memo (
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    var memo : String,
    var editMode : Boolean,
    var secretMode : Boolean, // secret Mode
    var secretPassWord : String
)