package com.flexteam.model

import com.google.gson.annotations.SerializedName

class MessageModel {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("owner")
    var owner: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("time")
    var time: String? = null

    @SerializedName("type")
    var type: Int? = 0

    @SerializedName("images")
    var images : ArrayList<String>? = null
}

enum class MessageType(var value: Int) {
    MyText(0),
    OtherText(1),
    MyImage(2),
    OtherImage(3)

}