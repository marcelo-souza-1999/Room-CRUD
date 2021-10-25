package com.marcelo.crudroom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marcelo.crudroom.database.entity.Subscriber

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM subscriber")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM subscriber")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}