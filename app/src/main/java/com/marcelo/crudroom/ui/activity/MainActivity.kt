package com.marcelo.crudroom.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelo.crudroom.R
import com.marcelo.crudroom.database.entity.UserEntity
import com.marcelo.crudroom.databinding.ActivityMainBinding
import com.marcelo.crudroom.ui.adapter.UserAdapter
import com.marcelo.crudroom.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var userViewModel: UserViewModel
    private val userViewModel: UserViewModel  by viewModel()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        initRecyclerView()

        userViewModel.message.observe(this, { messages ->
            messages.getContentIfNotHandled()?.let { event ->
                Toast.makeText(this, event, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupViewModel() {
        //val dao: UserDAO = UserDatabase.getInstance(application).userDAO
        //val repository = UserRepository(dao)
        //val factory = UserViewModel.ViewModelFactory(repository)
        //userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.myViewModel = userViewModel
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        binding.recyclerSubscribers.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter { selectedItem: UserEntity -> listItemClicked(selectedItem) }
        binding.recyclerSubscribers.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        userViewModel.subscribers.observe(this, { subscribers ->
            adapter.setList(subscribers)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(userEntity: UserEntity) {
        Toast.makeText(this, "Nome selecionado é ${userEntity.name}", Toast.LENGTH_LONG).show()
        userViewModel.initUpdateDelete(userEntity)
    }
}