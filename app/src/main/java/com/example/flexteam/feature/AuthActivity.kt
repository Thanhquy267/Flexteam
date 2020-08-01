package com.example.flexteam.feature

import com.example.flexteam.R
import com.example.flexteam.BR
import com.example.flexteam.base.BaseBindingModelActivity
import com.example.flexteam.databinding.ActivityAuthBinding
import com.example.flexteam.feature.login.LoginFragment

class AuthActivity : BaseBindingModelActivity<ActivityAuthBinding,AuthViewModel>(){
    override fun layoutId(): Int = R.layout.activity_auth
    override fun viewModelClass(): Class<AuthViewModel>  = AuthViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
      mViewModel.mActivityNavigator?.addFragment(R.id.fl_root,LoginFragment(),false)
    }
}