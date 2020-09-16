package com.pitems.livedata.data.repository

import com.pitems.livedata.data.NetworkApi.RetrofitInstance
import com.pitems.livedata.data.model.Post
import retrofit2.Response
import retrofit2.Retrofit

class PostRepository {
    suspend fun getPost(): Response<Post> {
       return RetrofitInstance.api.getpost()
    }

    suspend fun getPostNumber(number:Int):Response<Post>{
        return RetrofitInstance.api.getPostNumber(number)
    }

    suspend fun getCustomPost(userId:Int):Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId)
    }

    suspend fun getCustomPostQuery(userId:Int,sort:String,order:String):Response<List<Post>>{
        return RetrofitInstance.api.getCustomPostQuery(userId,sort,order)
    }

    suspend fun getCustomPostQueryMap(userId:Int,options:Map<String,String>):Response<List<Post>>{
        return RetrofitInstance.api.getCustomPostQueryMap(userId,options)
    }

    suspend fun pushPost(post:Post):Response<Post>{
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPostEncoded(userId:Int,id:Int,title:String,body:String):Response<Post>{
        return RetrofitInstance.api.pushPostEncoded(userId,id,title,body)
    }
}