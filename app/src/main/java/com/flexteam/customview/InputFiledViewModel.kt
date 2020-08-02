package com.flexteam.customview

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import io.reactivex.functions.Consumer

class InputFiledViewModel : BaseObservable() {
    @Bindable
    var mTitle = ObservableField("")
    @Bindable
    var mHint = ObservableField("")
    @Bindable
    var mError = ObservableField("")
    @Bindable
    var mContent = ObservableField("")
    var mTextChangeConsumer: Consumer<String>? = null

    @RequiresApi(Build.VERSION_CODES.N)
    fun onTextChanged() : TextWatcher{
        return object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mTextChangeConsumer?.accept(s.toString())
            }

        }
    }
}