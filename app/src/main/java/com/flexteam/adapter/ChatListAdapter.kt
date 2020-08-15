package com.flexteam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.R
import com.flexteam.databinding.LayoutItemChatListBinding
import com.flexteam.model.ChatListModel

interface OnChatClickListener {
    fun onChatClicked(item: ChatListModel?)
}

class ChatListAdapter(private val listChat: ArrayList<ChatListModel>, val listener: OnChatClickListener) : RecyclerView.Adapter<ChatListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        return ChatListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_item_chat_list, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return listChat.size
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(listChat[position])
    }

}

class ChatListViewHolder(private val binder: LayoutItemChatListBinding, val listener: OnChatClickListener) : RecyclerView.ViewHolder(binder.root) {
    fun bind(item: ChatListModel?) {
        binder.tvName.text = item?.title
        binder.tvLastMessage.text = item?.lastMessage
        binder.tvTime.text = item?.time
        binder.rlRoot.setOnClickListener {
            listener.onChatClicked(item)
        }
    }
}
