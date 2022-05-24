package com.devseok.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-03
 * @desc ROOM DB Class
 */
@Entity
data class Memo (
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    @ColumnInfo(name = "memo")
    var memo : String,
    var editMode : Boolean,
    var secretMode : Boolean, // secret Mode
    var secretPassWord : String,
    var secretEnabled : Boolean,
    var color : String,
    var mode : Boolean,
    var update_time : String
) : Serializable {

}