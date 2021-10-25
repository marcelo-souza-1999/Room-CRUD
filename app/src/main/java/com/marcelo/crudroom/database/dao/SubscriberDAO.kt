package com.marcelo.crudroom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marcelo.crudroom.database.entity.Subscriber

@Dao
interface SubscriberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}