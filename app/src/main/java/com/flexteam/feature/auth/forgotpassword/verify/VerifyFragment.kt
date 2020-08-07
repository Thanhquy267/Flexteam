package com.flexteam.feature.auth.forgotpassword.verify

import android.text.Editable
import android.text.TextWatcher
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentVerifyBinding

class VerifyFragment : BaseBindingModelFragment<FragmentVerifyBinding, VerifyViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_verify
    override fun viewModelClass(): Class<VerifyViewModel> = VerifyViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        initView()
        handleTextChange()
    }

    private fun initView() {
        mLayoutBinding.layoutActionBar.viewModel = mViewModel.mActionBarViewModel
    }

    private fun handleTextChange() {
        mLayoutBinding.etFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.checkValid()}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    mLayoutBinding.etSecond.requestFocus()
                }
            }
        })
        mLayoutBinding.etSecond.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.checkValid()}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1){
                    mLayoutBinding.etThird.requestFocus()
                }
            }

        })
        mLayoutBinding.etThird.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.checkValid()}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1){
                    mLayoutBinding.etFourth.requestFocus()
                }
            }

        })
        mLayoutBinding.etFourth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mViewModel.checkValid()}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}