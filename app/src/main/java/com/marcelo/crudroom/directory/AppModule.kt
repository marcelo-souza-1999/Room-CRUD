package com.marcelo.crudroom.directory

import androidx.room.Room
import com.marcelo.crudroom.database.app.UserDatabase
import com.marcelo.crudroom.repository.UserRepository
import com.marcelo.crudroom.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            "crud_database"
        ).build()
    }
}

val daoModule = module {
    single { get<UserDatabase>().userDAO() }

    single { UserRepository(get())}
}

val viewModule = module {
    viewModel {
        UserViewModel(get())
    }
}

