package com.flexteam.feature.login

import android.widget.Toast
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentLoginBinding

class LoginFragment : BaseBindingModelFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_login
    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
    }
}