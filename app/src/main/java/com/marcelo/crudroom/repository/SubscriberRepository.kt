package com.marcelo.crudroom.repository

import com.marcelo.crudroom.database.dao.SubscriberDAO
import com.marcelo.crudroom.database.entity.Subscriber

class SubscriberRepository(private val subscriberDAO: SubscriberDAO) {

    val subscribers = subscriberDAO.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) {
        subscriberDAO.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        subscriberDAO.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        subscriberDAO.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        subscriberDAO.deleteAll()
    }
}