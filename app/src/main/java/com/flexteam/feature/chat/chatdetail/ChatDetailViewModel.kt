package com.flexteam.feature.chat.chatdetail

import android.view.View
import androidx.databinding.ObservableField
import com.flexteam.adapter.ChatDetailAdapter
import com.flexteam.base.BaseViewModel
import com.flexteam.model.MessageModel
import com.flexteam.model.MessageType
import java.util.function.Consumer

class ChatDetailViewModel : BaseViewModel() {
    var mChatDetailAdapter : ChatDetailAdapter? = null
    var mListMessage = ArrayList<MessageModel>()
    var mMessage = ObservableField("")
    var mIsOwner = false
    var mNewMessageConsumer : Consumer<Unit>? = null

    fun onSendClick(view : View){
        if (mMessage.get()?.isNotEmpty()== true){
            val message = MessageModel().apply {
                owner = if (mIsOwner) null else "Thanh Quý"
                message = mMessage.get()
                time = "22:18"
                type = if (mIsOwner) MessageType.MyText.value else MessageType.OtherText.value
            }
            mChatDetailAdapter?.addMessage(message)
            mMessage.set("")
            mIsOwner = !mIsOwner
        }
        mNewMessageConsumer?.accept(Unit)
    }

    fun sendImage(listImage : ArrayList<String>){
        if (listImage.isNotEmpty()){
            val message = MessageModel().apply {
                owner = if (mIsOwner) null else "Thanh Quý"
                message = mMessage.get()
                time = "22:18"
                type = if (mIsOwner) MessageType.MyImage.value else MessageType.OtherImage.value
                images = listImage
            }
            mChatDetailAdapter?.addMessage(message)
            mIsOwner = !mIsOwner
        }
        mNewMessageConsumer?.accept(Unit)
    }
}