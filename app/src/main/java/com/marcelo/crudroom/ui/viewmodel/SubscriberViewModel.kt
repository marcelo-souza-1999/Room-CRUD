package com.marcelo.crudroom.ui.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marcelo.crudroom.database.entity.Subscriber
import com.marcelo.crudroom.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearDeleteButtonText = MutableLiveData<String>()

    init {
        saveUpdateButtonText.value = "Salvar"
        clearDeleteButtonText.value = "Apagar"
    }

    fun saveUpdate() {
        val name: String = inputName.value!!
        val email: String = inputEmail.value!!
        insert(Subscriber(0,name, email))

        inputName.value = null
        inputEmail.value = null
    }

    fun clearDelete() {
        clearAll()
    }

    private fun insert(subscriber: Subscriber){
        viewModelScope.launch {
            repository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.update(subscriber)
        }
    }

    fun delete(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.delete(subscriber)
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    class ViewModelFactory(val repository: SubscriberRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SubscriberViewModel::class.java))
                return SubscriberViewModel(repository) as T
            throw IllegalArgumentException("Classe ViewModel desconhecida")
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}