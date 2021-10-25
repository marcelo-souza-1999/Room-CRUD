package com.marcelo.crudroom.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.marcelo.crudroom.R
import com.marcelo.crudroom.database.app.SubscriberDatabase
import com.marcelo.crudroom.database.dao.SubscriberDAO
import com.marcelo.crudroom.databinding.ActivityMainBinding
import com.marcelo.crudroom.repository.SubscriberRepository
import com.marcelo.crudroom.ui.viewmodel.SubscriberViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscribeViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao : SubscriberDAO = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModel.ViewModelFactory(repository)
        subscribeViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscribeViewModel
        binding.lifecycleOwner = this
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscribeViewModel.subscribers.observe(this, { subscribers ->
            Log.d("testeBinding", subscribers.toString())
        })
    }
}