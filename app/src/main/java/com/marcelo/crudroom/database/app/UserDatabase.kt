package com.marcelo.crudroom.database.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marcelo.crudroom.database.dao.UserDAO
import com.marcelo.crudroom.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    //abstract val userDAO: UserDAO

    /*companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this){
                var instance:UserDatabase? = INSTANCE
                    if (instance==null){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            "crud_database"
                        ).build()
                    }
                return instance
            }
        }
    }*/
}