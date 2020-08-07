package com.flexteam.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.R
import com.flexteam.databinding.LayoutItemMyChatBinding
import com.flexteam.databinding.LayoutItemMyImageBinding
import com.flexteam.databinding.LayoutItemOtherChatBinding
import com.flexteam.databinding.LayoutItemOtherImageBinding
import com.flexteam.model.MessageModel
import com.flexteam.model.MessageType
import com.flexteam.utils.loadImage
import java.io.File


class ChatDetailAdapter(private val listMessage: ArrayList<MessageModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MessageType.MyText.value -> MyTextViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_my_chat,
                    parent,
                    false
                )
            )
            MessageType.OtherText.value -> OtherTextViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_other_chat,
                    parent,
                    false
                )
            )
            MessageType.MyImage.value -> MyImageViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_my_image,
                    parent,
                    false
                )
            )
            MessageType.OtherImage.value -> OtherImageViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_other_image,
                    parent,
                    false
                )
            )
            else -> MyTextViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_my_chat,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return listMessage.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyTextViewHolder -> holder.bind(listMessage[position])
            is MyImageViewHolder -> holder.bind(listMessage[position])
            is OtherTextViewHolder -> holder.bind(listMessage[position])
            is OtherImageViewHolder -> holder.bind(listMessage[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = listMessage[position]
        return if (message.owner == null) {
            if (message.type == MessageType.MyText.value) MessageType.MyText.value else MessageType.MyImage.value
        } else {
            if (message.type == MessageType.OtherText.value) MessageType.OtherText.value else MessageType.OtherImage.value
        }
    }

    fun addMessage(message: MessageModel) {
        this.listMessage.add(0, message)
        notifyItemInserted(0)
    }
}


class MyTextViewHolder(private val binder: LayoutItemMyChatBinding) :
    RecyclerView.ViewHolder(binder.root) {
    fun bind(item: MessageModel?) {
        binder.tvMessage.text = item?.message
        binder.tvTime.text = item?.time
    }
}

class MyImageViewHolder(private val binder: LayoutItemMyImageBinding) :
    RecyclerView.ViewHolder(binder.root) {
    fun bind(item: MessageModel?) {
        binder.tvTime.text = item?.time
        when(item?.images?.size){
            1 ->{
                binder.cvOneImage.visibility = View.VISIBLE
                binder.llTwoImage.visibility = View.GONE
                binder.llThreeImage.visibility = View.GONE
                binder.ivImageMessage.loadImage(item.images?.first())
                setImageSize(item.images?.first())
            }
            2 ->{
                binder.cvOneImage.visibility = View.GONE
                binder.llTwoImage.visibility = View.VISIBLE
                binder.llThreeImage.visibility = View.GONE
                binder.ivImage12.loadImage(item.images?.first())
                binder.ivImage22.loadImage(item.images?.get(1))
            }
            3 ->{
                binder.cvOneImage.visibility = View.GONE
                binder.llTwoImage.visibility = View.GONE
                binder.llThreeImage.visibility = View.VISIBLE
                binder.ivImage13.loadImage(item.images?.first())
                binder.ivImage23.loadImage(item.images?.get(1))
                binder.ivImage33.loadImage(item.images?.get(2))
            }
        }
    }
    fun setImageSize(image : String?) {
        val bitmap = BitmapFactory.decodeFile(image)
        val resource = binder.root.context.resources
        val imageWidth = resource.getDimension(R.dimen._230sdp)
        val imageHeight: Float
        val imageRatio = (bitmap.width.toFloat()) / (bitmap.height.toFloat())
        imageHeight = imageWidth * imageRatio
        binder.cvOneImage.layoutParams.width = imageWidth.toInt()
        binder.cvOneImage.layoutParams.height = imageHeight.toInt()
    }
}

class OtherTextViewHolder(private val binder: LayoutItemOtherChatBinding) :
    RecyclerView.ViewHolder(binder.root) {
    fun bind(item: MessageModel?) {
        binder.tvMessage.text = item?.message
        binder.tvTime.text = item?.time
        binder.tvOwner.text = item?.owner
    }

}

class OtherImageViewHolder(private val binder: LayoutItemOtherImageBinding) :
    RecyclerView.ViewHolder(binder.root) {
    fun bind(item: MessageModel?) {
        binder.tvTime.text = item?.time
        binder.tvOwner.text = item?.owner
        when(item?.images?.size){
            1 ->{
                binder.cvOneImage.visibility = View.VISIBLE
                binder.llTwoImage.visibility = View.GONE
                binder.llThreeImage.visibility = View.GONE
                binder.ivImageMessage.loadImage(item.images?.first())
                setImageSize(item.images?.first())
            }
            2 ->{
                binder.cvOneImage.visibility = View.GONE
                binder.llTwoImage.visibility = View.VISIBLE
                binder.llThreeImage.visibility = View.GONE
                binder.ivImage12.loadImage(item.images?.first())
                binder.ivImage22.loadImage(item.images?.get(1))
            }
            3 ->{
                binder.cvOneImage.visibility = View.GONE
                binder.llTwoImage.visibility = View.GONE
                binder.llThreeImage.visibility = View.VISIBLE
                binder.ivImage13.loadImage(item.images?.first())
                binder.ivImage23.loadImage(item.images?.get(1))
                binder.ivImage33.loadImage(item.images?.get(2))
            }
        }
    }

    fun setImageSize(image : String?) {
        val bitmap = BitmapFactory.decodeFile(image)
        val resource = binder.root.context.resources
        val imageWidth = resource.getDimension(R.dimen._230sdp)
        val imageHeight: Float
        val imageRatio = (bitmap.width.toFloat()) / (bitmap.height.toFloat())
        imageHeight = imageWidth * imageRatio
        binder.cvOneImage.layoutParams.width = imageWidth.toInt()
        binder.cvOneImage.layoutParams.height = imageHeight.toInt()
    }
}
