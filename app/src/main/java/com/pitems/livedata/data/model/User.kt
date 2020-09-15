package com.pitems.livedata.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
//It will be possible with parcelable to send the user class to another fragment
@Parcelize
@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_id")
    val id: Int,

    @ColumnInfo(name="user_name")
    val name: String,

    @ColumnInfo(name="user_lastname")
    val lastname:String,

    @ColumnInfo(name = "user_age")
    val age:Int
): Parcelable