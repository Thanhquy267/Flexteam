package com.flexteam.binding

import androidx.databinding.DataBindingComponent

class AppDataBindingComponent: DataBindingComponent {
    override fun getBindingAdapters(): BindingAdapters {
        return BindingAdapters()
    }
}