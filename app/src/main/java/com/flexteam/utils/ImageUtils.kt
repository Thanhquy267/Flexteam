package com.flexteam.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(image : Any?){
    Glide.with(this).load(image).into(this)
}