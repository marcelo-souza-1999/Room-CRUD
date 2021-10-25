package com.marcelo.crudroom.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelo.crudroom.R
import com.marcelo.crudroom.database.app.SubscriberDatabase
import com.marcelo.crudroom.database.dao.SubscriberDAO
import com.marcelo.crudroom.database.entity.Subscriber
import com.marcelo.crudroom.databinding.ActivityMainBinding
import com.marcelo.crudroom.repository.SubscriberRepository
import com.marcelo.crudroom.ui.adapter.SubscriberAdapter
import com.marcelo.crudroom.ui.viewmodel.SubscriberViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscribeViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        initRecyclerView()
    }

    private fun setupViewModel() {
        val dao: SubscriberDAO = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModel.ViewModelFactory(repository)
        subscribeViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscribeViewModel
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        binding.recyclerSubscribers.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscribeViewModel.subscribers.observe(this, { subscribers ->
            binding.recyclerSubscribers.adapter =
                SubscriberAdapter(subscribers) { selectedItem: Subscriber ->
                    listItemClicked(selectedItem)
                }
        })
    }

    private fun listItemClicked(subscriber: Subscriber) {
        Toast.makeText(this, "Nome selecionado é ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscribeViewModel.initUpdateDelete(subscriber)
    }
}