package com.marcelo.crudroom.ui.viewmodel

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.marcelo.crudroom.database.entity.UserEntity
import com.marcelo.crudroom.repository.UserRepository
import com.marcelo.crudroom.ui.treatment.Events
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    private var isUpdateDelete = false
    private lateinit var userEntityToUpdateDelete: UserEntity

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Events<String>>()

    val message: LiveData<Events<String>>
        get() = statusMessage

    init {
        saveUpdateButtonText.value = "Salvar"
        clearDeleteButtonText.value = "Apagar"
    }

    fun saveUpdate() {
        if (inputName.value.isNullOrBlank()) {
            statusMessage.value = Events("Por favor insira o nome do Usuário!")
        } else if (inputEmail.value.isNullOrBlank()) {
            statusMessage.value = Events("Por favor insira o email do Usuário!")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Events("Por favor insira um email válido!")
        } else {
            if (isUpdateDelete) {
                userEntityToUpdateDelete.name = inputName.value!!
                userEntityToUpdateDelete.email = inputEmail.value!!
                update(userEntityToUpdateDelete)
            } else {
                val name: String = inputName.value!!
                val email: String = inputEmail.value!!
                insert(UserEntity(0, name, email))

                inputName.value = null
                inputEmail.value = null
            }
        }

    }

    fun clearDelete() {
        if (isUpdateDelete) {
            delete(userEntityToUpdateDelete)
        } else {
            clearAll()
        }
    }

    private fun insert(userEntity: UserEntity) {
        viewModelScope.launch {
            val getRowModified: Long = repository.insert(userEntity)
            if (getRowModified > 0) {
                statusMessage.value = Events("Usuário ${userEntity.name} inserido com Sucesso!")
            } else {
                statusMessage.value =
                    Events("Não foi possivel cadastrar o usuário ${userEntity.name}!")
            }
        }
    }

    private fun update(userEntity: UserEntity) {
        viewModelScope.launch {
            val getRowUpdated: Int = repository.update(userEntity)
            if (getRowUpdated > 0) {
                inputName.value = null
                inputEmail.value = null
                isUpdateDelete = false
                saveUpdateButtonText.value = "Salvar"
                clearDeleteButtonText.value = "Apagar"

                statusMessage.value =
                    Events("Dados do usuário ${userEntity.name} atualizado com Sucesso!")
            } else {
                statusMessage.value =
                    Events("Erro ao atualizar dados do usuário ${userEntity.name}!")
            }
        }
    }

    private fun delete(userEntity: UserEntity) {
        viewModelScope.launch {
            val getRowDeleted: Int = repository.delete(userEntity)
            if (getRowDeleted > 0) {
                inputName.value = null
                inputEmail.value = null
                isUpdateDelete = false
                saveUpdateButtonText.value = "Salvar"
                clearDeleteButtonText.value = "Apagar"

                statusMessage.value = Events("Usuário ${userEntity.name} deletado com Sucesso!")
            } else {
                statusMessage.value =
                    Events("Não foi possivel apagar o usuário ${userEntity.name}!")
            }
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            val getRowsDeleted: Int = repository.deleteAll()
            if (getRowsDeleted > 0) {

                statusMessage.value = Events("Usuários apagados com Sucesso!")
            } else {
                statusMessage.value = Events("Erro ao apagar usuários!")
            }
        }
    }

    fun initUpdateDelete(userEntity: UserEntity) {
        inputName.value = userEntity.name
        inputEmail.value = userEntity.email
        isUpdateDelete = true
        userEntityToUpdateDelete = userEntity
        saveUpdateButtonText.value = "Atualizar"
        clearDeleteButtonText.value = "Apagar"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    class ViewModelFactory(val repository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java))
                return UserViewModel(repository) as T
            throw IllegalArgumentException("Classe ViewModel desconhecida")
        }
    }
}