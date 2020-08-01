package com.example.flexteam.feature.login

import com.example.flexteam.BR
import com.example.flexteam.R
import com.example.flexteam.base.BaseBindingModelFragment
import com.example.flexteam.databinding.FragmentLoginBinding

class LoginFragment : BaseBindingModelFragment<FragmentLoginBinding,LoginViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_login
    override fun viewModelClass(): Class<LoginViewModel>  = LoginViewModel::class.java
    override fun bindingVariable(): Int  = BR.viewModel
}