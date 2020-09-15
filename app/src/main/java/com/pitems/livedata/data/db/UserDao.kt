package com.pitems.livedata.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pitems.livedata.data.model.User

@Dao
interface UserDao {

    //In case the id conflict in the project we can either replace it or ignore it as a strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE) //Remember that suspend is for async task
    suspend fun insertSubscriber(user: User): Long

    @Update
    suspend fun updateSubscriber(user: User): Int

    @Delete
    suspend fun deleteSubscriber(user: User) :Int

    @Query("DELETE FROM user_table")
    suspend fun deleteAll():Int

    //Because this returns a livedata object there's no need to make it suspend it already works on another thread
    @Query("SELECT * FROM user_table ORDER BY user_id ASC")
    fun getAllData():LiveData<List<User>>
}