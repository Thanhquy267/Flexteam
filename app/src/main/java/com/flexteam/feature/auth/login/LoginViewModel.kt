package com.flexteam.feature.auth.login

import android.view.View
import androidx.databinding.ObservableField
import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.InputFiledViewModel
import com.flexteam.feature.auth.forgotpassword.ForgotPasswordActivity
import com.flexteam.feature.home.HomeActivity
import com.flexteam.feature.auth.register.RegisterFragment
import com.flexteam.utils.Utils
import com.flexteam.utils.isValidEmail
import com.flexteam.utils.isValidPassword
import io.reactivex.functions.Consumer

class LoginViewModel : BaseViewModel() {
    var mEmailViewModel = InputFiledViewModel()
    var mPasswordViewModel = InputFiledViewModel()
    var mIsEnableButton = ObservableField(false)

    override fun onReady() {
        super.onReady()
        //Email Field
        mEmailViewModel.mTitle.set("Email")
        mEmailViewModel.mHint.set("Your email")
        mEmailViewModel.mTextChangeConsumer = Consumer { email ->
            checkValid()
            if (email.isEmpty()) {
                mEmailViewModel.mError.set("Email is require")
            } else {
                if (!email.isValidEmail()) {
                    mEmailViewModel.mError.set("Email is valid")
                } else {
                    mEmailViewModel.mError.set("")
                }
            }
        }
        //Password Field
        mPasswordViewModel.mTitle.set("Password")
        mPasswordViewModel.mHint.set("Your password")
        mPasswordViewModel.mTextChangeConsumer = Consumer { password ->
            checkValid()
            if (password.isEmpty()) {
               mPasswordViewModel.mError.set("Password is require")
            } else {
                if (!password.isValidPassword()) {
                   mPasswordViewModel.mError.set("Password is valid")
                } else {
                   mPasswordViewModel.mError.set("")
                }
            }

        }
    }

    private fun checkValid(){
        val email = mEmailViewModel.mContent.get()
        val password = mPasswordViewModel.mContent.get()
        mIsEnableButton.set(email?.isNotEmpty() == true && email.isValidEmail())
        mIsEnableButton.set(password?.isNotEmpty() == true && password.isValidPassword())
    }

    fun onLoginClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mActivityNavigator?.startActivity(HomeActivity())
    }

    fun onForgotPasswordClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mActivityNavigator?.startActivity(ForgotPasswordActivity())
    }


    fun onRegisterClick(view : View){
        Utils.hideKeyboard(view.context,view)
        mActivityNavigator?.addFragment(R.id.fl_root, RegisterFragment(), true)
    }
}