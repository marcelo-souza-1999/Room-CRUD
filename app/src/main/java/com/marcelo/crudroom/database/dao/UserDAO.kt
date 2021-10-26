package com.marcelo.crudroom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marcelo.crudroom.database.entity.UserEntity

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(userEntity: UserEntity) : Long

    @Update
    suspend fun updateSubscriber(userEntity: UserEntity) : Int

    @Delete
    suspend fun deleteSubscriber(userEntity: UserEntity) : Int

    @Query("DELETE FROM subscriber")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM subscriber")
    fun getAllSubscribers():LiveData<List<UserEntity>>
}