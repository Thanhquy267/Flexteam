package com.flexteam.feature.auth.forgotpassword.verify

import android.view.View
import androidx.databinding.ObservableField
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.utils.Utils
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