package com.pitems.livedata.data.viewmodel.DemoRoom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pitems.livedata.data.repository.PostRepository

class RetrofitDemoViewModelFactory(private  val repository: PostRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RetrofitDemoViewModel(repository) as T
    }
}