package com.flexteam.model

import com.google.gson.annotations.SerializedName

class TaskModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("status")
    var status: Int? = 0

}