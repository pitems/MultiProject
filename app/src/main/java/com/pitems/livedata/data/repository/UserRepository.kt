package com.pitems.livedata.data.repository


import com.pitems.livedata.data.model.User
import com.pitems.livedata.data.db.UserDao

class UserRepository(private val dao: UserDao) {

    val users = dao.getAllData()

    suspend fun insert(user: User):Long{
       return dao.insertSubscriber(user)
    }

    suspend fun update(user: User):Int{
       return dao.updateSubscriber(user)
    }

    suspend fun delete (user: User):Int{
        return dao.deleteSubscriber(user)
    }

    suspend fun deleteAll():Int{
       return dao.deleteAll()
    }
}