package com.pitems.livedata.data.model

//Try to use the same names of the variables as the one on the JSON files or we have to use the following annotation
data class Post(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
)