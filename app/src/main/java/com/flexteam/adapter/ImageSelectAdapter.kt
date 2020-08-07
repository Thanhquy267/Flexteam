package com.flexteam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.R
import com.flexteam.databinding.LayoutItemImageBinding
import com.flexteam.model.ImageSelectorModel
import com.flexteam.utils.loadImage

class ImageSelectAdapter(
    images: ArrayList<ImageSelectorModel>?,
    val listener: OnImageClickListener
) : RecyclerView.Adapter<ImageViewHolder>() {
    var dataList: ArrayList<ImageSelectorModel>? = images
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding: LayoutItemImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_item_image,
            parent,
            false
        )
        return ImageViewHolder(binding, listener, dataList)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindData(dataList?.get(position))
    }

    fun setData(images: ArrayList<ImageSelectorModel>?) {
        if (!images.isNullOrEmpty()) {
            dataList?.clear()
            dataList?.addAll(images)
            notifyDataSetChanged()
        }
    }

    fun selectImage(images: ImageSelectorModel, position: Int) {
        dataList?.set(position, images)
        notifyItemChanged(position)
    }

    interface OnImageClickListener {
        fun onImageClick(item: ImageSelectorModel?, position: Int)
    }
}

class ImageViewHolder(
    val binding: LayoutItemImageBinding,
    val listener: ImageSelectAdapter.OnImageClickListener,
    var dataList: ArrayList<ImageSelectorModel>?
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(item: ImageSelectorModel?) {
        if (item?.imagePath == "access-camera") {
            binding.ivImage.loadImage(R.drawable.ic_video_call)
            binding.ivImage.layoutParams.apply {
                height = binding.root.context.resources.getDimensionPixelSize(R.dimen._60sdp)
                width = binding.root.context.resources.getDimensionPixelSize(R.dimen._60sdp)
            }
            binding.cvCount.visibility = View.GONE
        } else {
            binding.ivImage.loadImage(item?.imagePath)
            binding.ivImage.layoutParams.apply {
                height = binding.root.context.resources.getDimensionPixelSize(R.dimen._100sdp)
                width = binding.root.context.resources.getDimensionPixelSize(R.dimen._100sdp)
            }
        }

        if (item?.isChecked == false) {
            binding.cvCount.visibility = View.GONE
        } else {
            binding.cvCount.visibility = View.VISIBLE
        }

        binding.tvCount.text = dataList?.filter { it.isChecked }?.size.toString()
        binding.root.setOnClickListener {
            if (item?.isChecked == false){
                if (dataList?.filter { it.isChecked }?.size?:0 < 3){
                    onItemClick(item)
                }
            }else{
                onItemClick(item)
            }
        }
    }

    private fun onItemClick(item: ImageSelectorModel?) {
        listener.onImageClick(item, adapterPosition)
    }
}