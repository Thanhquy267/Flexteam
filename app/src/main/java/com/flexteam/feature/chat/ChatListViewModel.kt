package com.flexteam.feature.chat

import com.flexteam.adapter.ChatListAdapter
import com.flexteam.base.BaseViewModel
import com.flexteam.model.ChatListModel

class ChatListViewModel : BaseViewModel(){
    var mChatListAdapter : ChatListAdapter? = null
    var mChatList = ArrayList<ChatListModel>()

    fun dummyData(){
        mChatList.add(ChatListModel().apply {
            this.title = "Flexteam"
            this.lastMessage = "Đức: Ok"
            this.time = "12:30"
        })
        mChatList.add(ChatListModel().apply {
            this.title = "Đức Lê"
            this.lastMessage = "Testing message"
            this.time = "12:30"
        })
        mChatList.add(ChatListModel().apply {
            this.title = "Vĩ Khang"
            this.lastMessage = "Tôi: Ok"
            this.time = "12:30"
        })
        mChatList.add(ChatListModel().apply {
            this.title = "Khang Hồ"
            this.lastMessage = "123!!!"
            this.time = "12:30"
        })
    }
}