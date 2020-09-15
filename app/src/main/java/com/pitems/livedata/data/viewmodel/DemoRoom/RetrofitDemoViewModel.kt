package com.pitems.livedata.data.viewmodel.DemoRoom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pitems.livedata.data.model.Post
import com.pitems.livedata.data.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RetrofitDemoViewModel(private val repository: PostRepository): ViewModel(){

    val myResponse:MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponseNumber:MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponseCustom:MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myResponseCustomMap:MutableLiveData<Response<List<Post>>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value=response
        }
    }

    fun getPostNumber(number:Int){
        viewModelScope.launch {
            val response = repository.getPostNumber(number)
            myResponseNumber.value=response
        }
    }

    fun getCustomPost(userId:Int){
        viewModelScope.launch {
            val response = repository.getCustomPost(userId)
            myResponseCustom.value = response

        }
    }

    fun getCustomPostQuery(userId:Int,sort:String,order:String){
        viewModelScope.launch {
            val response = repository.getCustomPostQuery(userId,sort,order)
            myResponseCustom.value = response

        }
    }

    fun getCustomPostQueryMap(userId:Int,options:Map<String,String>){
        viewModelScope.launch {
            val response = repository.getCustomPostQueryMap(userId,options)
            myResponseCustomMap.value = response

        }
    }

}