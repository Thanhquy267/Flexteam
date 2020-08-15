package com.flexteam.model

import com.google.gson.annotations.SerializedName

class NotificationModel {
    @SerializedName("id")
    var id : Int = 0
    @SerializedName("title")
    var title: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("tag")
    var tag: String? = null
    @SerializedName("image_link")
    var image: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("createdAt")
    var createdAt: String? = null
    @SerializedName("updatedAt")
    var updatedAt: String? = null

}