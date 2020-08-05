package com.flexteam.customview

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.flexteam.utils.Utils
import io.reactivex.functions.Consumer

@RequiresApi(Build.VERSION_CODES.N)
class ActionBarViewModel : BaseObservable(){
    var mTitle = ObservableField("")
    var mShouldShowStartIcon = ObservableField(false)
    var mShouldShowEndIcon = ObservableField(false)
    var mStartIconClickConsumer : Consumer<Unit>? = null
    var mEndIconClickConsumer : Consumer<Unit>? = null


    fun onStartIconClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mStartIconClickConsumer?.accept(Unit)
    }

    fun onEndIconClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mEndIconClickConsumer?.accept(Unit)
    }
}