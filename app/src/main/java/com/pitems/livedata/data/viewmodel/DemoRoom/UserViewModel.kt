package com.pitems.livedata.data.viewmodel.DemoRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pitems.livedata.data.model.User
import com.pitems.livedata.data.db.UserDatabase
import com.pitems.livedata.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application):AndroidViewModel(application){
    //Using the Repository to do the Hard Work
    var readAllData:LiveData<List<User>>
    private val repository:UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao
        repository = UserRepository(userDao)
        readAllData = repository.users
    }

    //TODO function that will check the repository once resumed and send a object of type List<Users>

fun addUser(user: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(user)
            readAllData= repository.users
        }
    }

fun updateUser(user:User){
    viewModelScope.launch(Dispatchers.IO) {
        repository.update(user)
    }
}
    fun deleteUser(user:User){
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(user)
        }
    }
}