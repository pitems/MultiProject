package com.pitems.livedata.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pitems.livedata.data.model.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    //We should have only one instance of this
    abstract val userDao: UserDao

    companion object{
        @Volatile //Visible to all objects
        private var INSTANCE: UserDatabase? =null
        fun getDatabase(context: Context): UserDatabase {
            synchronized(this){
                var instance=
                    INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).build()
                }
                return instance
            }
        }
    }
}