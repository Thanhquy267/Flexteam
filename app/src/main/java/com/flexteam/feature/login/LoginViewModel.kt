package com.flexteam.feature.login

import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.InputFiledViewModel
import io.reactivex.functions.Consumer

class LoginViewModel : BaseViewModel() {
   var mEmailViewModel = InputFiledViewModel()
   var mPasswordViewModel = InputFiledViewModel()

   override fun onReady() {
      super.onReady()
      mEmailViewModel.mTitle.set("Email")
      mEmailViewModel.mHint.set("Your email")
      mEmailViewModel.mTextChangeConsumer = Consumer {
         mEmailViewModel.mError.set(if (it.isEmpty()) "Email is require" else "")

      }

      mPasswordViewModel.mTitle.set("Password")
      mPasswordViewModel.mHint.set("Your password")
      mPasswordViewModel.mTextChangeConsumer = Consumer {
         mPasswordViewModel.mError.set(if (it.isEmpty()) "Password is require" else "")
      }
   }
}