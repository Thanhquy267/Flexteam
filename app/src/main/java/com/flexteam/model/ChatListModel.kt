package com.flexteam.model

import com.google.gson.annotations.SerializedName

class ChatListModel {

    @SerializedName("id")
    var id : Int = 0

    @SerializedName("title")
    var title : String? = null

    @SerializedName("lastMessage")
    var lastMessage : String? = null

    @SerializedName("time")
    var time : String? = null

}