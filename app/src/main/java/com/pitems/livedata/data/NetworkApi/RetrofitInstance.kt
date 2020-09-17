package com.pitems.livedata.data.NetworkApi

import com.pitems.livedata.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Remember Objects are Singletons on Kotlin
object RetrofitInstance {
    //Represent our OkHttpClient
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    //Adding the client here will allow us to use our interceptor class and send the headers on our request be it POST, GET, DELETE, etc
    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

val api : SimpleApi by lazy {
    retrofit.create(SimpleApi::class.java)
}
}