package com.flexteam.feature.auth.login

import android.text.InputType
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentLoginBinding

@Suppress("DEPRECATION")
class LoginFragment : BaseBindingModelFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_login
    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        initView()
    }

    private fun initView() {
        mLayoutBinding.layoutEmail.viewModel = mViewModel.mEmailViewModel
        mLayoutBinding.layoutPassword.viewModel = mViewModel.mPasswordViewModel
        mLayoutBinding.layoutPassword.tlInput.isPasswordVisibilityToggleEnabled = true
        mLayoutBinding.layoutPassword.etInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
}