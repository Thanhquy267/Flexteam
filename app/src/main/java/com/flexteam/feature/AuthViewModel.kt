package com.flexteam.feature

import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.feature.login.LoginFragment

class AuthViewModel : BaseViewModel() {
    override fun onReady() {
        super.onReady()
        mActivityNavigator?.addFragment(R.id.fl_root, LoginFragment(),false)
    }
}