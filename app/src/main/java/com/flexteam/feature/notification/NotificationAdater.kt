package com.flexteam.feature.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexteam.R
import com.flexteam.databinding.LayoutItemNotificationBinding
import com.flexteam.model.NotificationModel
import java.util.*

class NotificationAdapter(private var listNotification: ArrayList<NotificationModel>?) :
    RecyclerView.Adapter<NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_notification,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listNotification?.size ?: 0
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindData(listNotification?.get(position))
    }


    fun setData(newData: ArrayList<NotificationModel>?) {
        listNotification = newData
        notifyDataSetChanged()
    }
}

class NotificationViewHolder(private var binding: LayoutItemNotificationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(notification: NotificationModel?) {
        binding.tvTitle.text = notification?.title
        binding.tvDescription.text = notification?.description
        binding.tvTag.text = notification?.tag
        binding.tvTime.text = "22:30"
        binding.tvDate.text = "15/08/2020"
    }
}