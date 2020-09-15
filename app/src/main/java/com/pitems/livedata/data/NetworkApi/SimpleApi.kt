package com.pitems.livedata.data.NetworkApi

import com.pitems.livedata.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SimpleApi {

    @GET("posts/1")
    //suspend fun getpost(): Post If we leave it like this the application will crash if we have any connection issue like the api has changed name
    suspend fun getpost(): Response<Post>

    //Get data from multiple api just adding the endpoint, so we can reutilize this same get option with different values
    @GET("posts/{postNumber}")
    suspend fun getPostNumber(
        @Path("postNumber") number: Int
    ): Response<Post>
    // Query /posts?userId=3

     @GET("posts")
     suspend fun getCustomPost(
         @Query("userId")userId:Int
     ):Response<List<Post>>

    //We will add extra Queries to our function
    @GET("posts")
    suspend fun getCustomPostQuery(
        @Query("userId")userId:Int,
        @Query("_sort") sort:String,
        @Query("_order")order:String
    ):Response<List<Post>>

    //Query Map
    @GET("posts")
    suspend fun getCustomPostQueryMap(
        @Query("userId")userId:Int,
        @QueryMap options:Map<String,String>
    ):Response<List<Post>>
}