package com.flexteam.feature.forgotpassword

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentForgotPasswordBinding

@Suppress("DEPRECATION")
class ForgotPasswordFragment : BaseBindingModelFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_forgot_password
    override fun viewModelClass(): Class<ForgotPasswordViewModel> = ForgotPasswordViewModel::class.java

    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        initView()
    }

    private fun initView() {
        mLayoutBinding.layoutActionBar.viewModel = mViewModel.mActionBarViewModel
        mLayoutBinding.layoutEmail.viewModel = mViewModel.mEmailViewModel
    }
}