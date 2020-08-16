package com.flexteam.feature.home.profile

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentProfileBinding

class ProfileFragment : BaseBindingModelFragment<FragmentProfileBinding,ProfileViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_profile
    override fun viewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mLayoutBinding.layoutActionBar.viewModel = mViewModel.mActionBarViewModel
    }
}