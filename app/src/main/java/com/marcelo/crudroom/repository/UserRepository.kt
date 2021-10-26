package com.marcelo.crudroom.repository

import com.marcelo.crudroom.database.dao.UserDAO
import com.marcelo.crudroom.database.entity.UserEntity

class UserRepository(private val userDAO: UserDAO) {

    val subscribers = userDAO.getAllSubscribers()

    suspend fun insert(userEntity: UserEntity): Long {
        return userDAO.insertSubscriber(userEntity)
    }

    suspend fun update(userEntity: UserEntity): Int{
        return userDAO.updateSubscriber(userEntity)
    }

    suspend fun delete(userEntity: UserEntity): Int{
        return userDAO.deleteSubscriber(userEntity)
    }

    suspend fun deleteAll(): Int{
        return userDAO.deleteAll()
    }
}