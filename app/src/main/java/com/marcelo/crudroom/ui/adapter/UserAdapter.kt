package com.marcelo.crudroom.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.crudroom.R
import com.marcelo.crudroom.database.entity.UserEntity
import com.marcelo.crudroom.databinding.ListUsersBinding

class UserAdapter(private val clickListener: (UserEntity) -> Unit) :
    RecyclerView.Adapter<SubscriberViewHolder>() {

    private val usersList = ArrayList<UserEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListUsersBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_users, parent, false)

        return SubscriberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

    fun setList(users: List<UserEntity>) {
        usersList.clear()
        usersList.addAll(users)
    }

}

class SubscriberViewHolder(private val binding: ListUsersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(userEntity: UserEntity, clickListener: (UserEntity) -> Unit) {
        binding.nameTextView.text = userEntity.name
        binding.emailTextView.text = userEntity.email
        binding.listItemLayout.setOnClickListener {
            clickListener(userEntity)
        }
    }
}