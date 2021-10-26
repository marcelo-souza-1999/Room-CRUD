package com.marcelo.crudroom.app

import android.app.Application
import com.marcelo.crudroom.directory.daoModule
import com.marcelo.crudroom.directory.databaseModule
import com.marcelo.crudroom.directory.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CrudRoomApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CrudRoomApp)

            modules(listOf(
                databaseModule,
                daoModule,
                viewModule
            ))
        }
    }
}