package com.flexteam.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

class BindingAdapters {
    @BindingAdapter("bind:setImageSource")
    fun setImageSource(imageView: AppCompatImageView, image: Int) {
        imageView.setImageResource(image)
    }
}