package com.marcelo.crudroom.database.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marcelo.crudroom.database.dao.SubscriberDAO
import com.marcelo.crudroom.database.entity.Subscriber

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this){
                var instance:SubscriberDatabase? = INSTANCE
                    if (instance==null){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            SubscriberDatabase::class.java,
                            "subscriber_database"
                        ).build()
                    }
                return instance
            }
        }
    }

}