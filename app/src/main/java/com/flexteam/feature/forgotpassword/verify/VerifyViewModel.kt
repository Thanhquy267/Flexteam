package com.flexteam.feature.forgotpassword.verify

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.ObservableField
import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.utils.Utils
import com.flexteam.utils.isValidEmail
import com.flexteam.utils.isValidPassword
import io.reactivex.functions.Consumer

class VerifyViewModel : BaseViewModel() {
    val mActionBarViewModel = ActionBarViewModel()
    var mIsEnableButton = ObservableField(false)
    var mFirst = ObservableField("")
    var mSecond = ObservableField("")
    var mThird = ObservableField("")
    var mFourth = ObservableField("")

    override fun onReady() {
        super.onReady()
        //Action Bar
        mActionBarViewModel.mTitle.set("Verification")
        mActionBarViewModel.mShouldShowStartIcon.set(true)
        mActionBarViewModel.mShouldShowEndIcon.set(false)
        mActionBarViewModel.mStartIconClickConsumer = Consumer {
            mActivityNavigator?.popFragment()
        }
    }

    fun checkValid(){
       mIsEnableButton.set(mFirst.get()?.isNotEmpty()!! && mSecond.get()?.isNotEmpty() !! && mThird.get()?.isNotEmpty() !! && mFourth.get()?.isNotEmpty()!! )
    }

    fun onSubmitClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mActivityNavigator?.finishActivity()
    }
}