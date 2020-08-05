package com.flexteam.feature.forgotpassword

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.base.BaseViewModel
import com.flexteam.databinding.ActivityForgotPasswordBinding
import com.flexteam.feature.AuthViewModel

class ForgotPasswordActivity : BaseBindingModelActivity<ActivityForgotPasswordBinding,BaseViewModel>() {
    override fun layoutId(): Int = R.layout.activity_forgot_password
    override fun viewModelClass(): Class<BaseViewModel> = BaseViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mViewModel.mActivityNavigator?.addFragment(R.id.rl_root, ForgotPasswordFragment(),false)
    }
}