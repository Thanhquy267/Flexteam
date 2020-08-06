package com.flexteam.feature.register

import android.text.InputType
import android.text.method.PasswordTransformationMethod
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentRegisterBinding

class RegisterFragment : BaseBindingModelFragment<FragmentRegisterBinding, RegisterViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_register
    override fun viewModelClass(): Class<RegisterViewModel> = RegisterViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mLayoutBinding.layoutFirstName.viewModel = mViewModel.mFirstNameViewModel
        mLayoutBinding.layoutLastName.viewModel = mViewModel.mLastNameViewModel
        mLayoutBinding.layoutEmail.viewModel = mViewModel.mEmailViewModel
        mLayoutBinding.layoutPhoneNumber.viewModel = mViewModel.mPhoneNumberViewModel
        mLayoutBinding.layoutPassword.viewModel = mViewModel.mPasswordViewModel
        mLayoutBinding.layoutConfirmPassword.viewModel = mViewModel.mConfirmPasswordViewModel
        //
        mLayoutBinding.layoutPhoneNumber.etInput.inputType = InputType.TYPE_CLASS_NUMBER
        mLayoutBinding.layoutPassword.etInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        mLayoutBinding.layoutPassword.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
        mLayoutBinding.layoutConfirmPassword.etInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        mLayoutBinding.layoutConfirmPassword.etInput.transformationMethod = PasswordTransformationMethod.getInstance()

    }
}