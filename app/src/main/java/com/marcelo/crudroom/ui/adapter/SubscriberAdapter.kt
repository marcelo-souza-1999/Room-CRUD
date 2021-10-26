package com.marcelo.crudroom.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.crudroom.R
import com.marcelo.crudroom.database.entity.Subscriber
import com.marcelo.crudroom.databinding.ListSubscribersBinding

class SubscriberAdapter(
    private val subscribersList: List<Subscriber>,
    private val clickListener: (Subscriber) -> Unit,
) :
    RecyclerView.Adapter<SubscriberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListSubscribersBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_subscribers, parent, false)

        return SubscriberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

}

class SubscriberViewHolder(val binding: ListSubscribersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener{click->
            clickListener(subscriber)
        }
    }
}