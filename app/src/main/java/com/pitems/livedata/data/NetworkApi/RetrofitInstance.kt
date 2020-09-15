package com.pitems.livedata.data.NetworkApi

import com.pitems.livedata.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Remember Objects are Singletons on Kotlin
object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

val api : SimpleApi by lazy {
    retrofit.create(SimpleApi::class.java)
}
}