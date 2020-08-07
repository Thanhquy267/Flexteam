package com.flexteam.feature

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.databinding.ActivityAuthBinding
import com.flexteam.feature.register.RegisterFragment

class AuthActivity : BaseBindingModelActivity<ActivityAuthBinding, AuthViewModel>() {
    override fun layoutId(): Int = R.layout.activity_auth
    override fun viewModelClass(): Class<AuthViewModel> = AuthViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mViewModel.mActivityNavigator?.addFragment(R.id.fl_root, RegisterFragment(), false)
    }
}