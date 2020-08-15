package com.flexteam.model

import com.google.gson.annotations.SerializedName

class ProjectModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("members")
    var members: Int? = 0

}