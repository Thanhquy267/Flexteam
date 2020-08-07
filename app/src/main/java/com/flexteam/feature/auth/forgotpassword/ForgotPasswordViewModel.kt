package com.flexteam.feature.auth.forgotpassword

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.customview.InputFiledViewModel
import com.flexteam.feature.auth.forgotpassword.verify.VerifyFragment
import com.flexteam.utils.Utils
import com.flexteam.utils.isValidEmail
import io.reactivex.functions.Consumer

@RequiresApi(Build.VERSION_CODES.N)
class ForgotPasswordViewModel : BaseViewModel() {
    val mEmailViewModel = InputFiledViewModel()
    val mActionBarViewModel = ActionBarViewModel()
    var mIsEnableButton = ObservableField(false)

    override fun onReady() {
        super.onReady()
        //Action Bar
        mActionBarViewModel.mTitle.set("Forgot password")
        mActionBarViewModel.mShouldShowStartIcon.set(true)
        mActionBarViewModel.mShouldShowEndIcon.set(false)
        mActionBarViewModel.mStartIconClickConsumer = Consumer {
            mActivityNavigator?.popFragment()
        }

        //Email Field
        mEmailViewModel.mTitle.set("Email")
        mEmailViewModel.mHint.set("Your email")
        mEmailViewModel.mTextChangeConsumer = Consumer { email ->
            if (email.isEmpty()) {
                mEmailViewModel.mError.set("Email is require")
            } else {
                if (!email.isValidEmail()) {
                    mEmailViewModel.mError.set("Email is valid")
                } else {
                    mEmailViewModel.mError.set("")
                }
            }
            mIsEnableButton.set(email.isNotEmpty() && email.isValidEmail())
        }
    }

    fun onSubmitClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mActivityNavigator?.addFragment(R.id.rl_root, VerifyFragment(),true)
    }
}