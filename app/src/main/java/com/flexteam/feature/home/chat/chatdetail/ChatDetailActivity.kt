package com.flexteam.feature.home.chat.chatdetail

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.base.BaseViewModel
import com.flexteam.databinding.ActivityChatDetailBinding

class ChatDetailActivity : BaseBindingModelActivity<ActivityChatDetailBinding,BaseViewModel>() {
    override fun layoutId(): Int = R.layout.activity_chat_detail
    override fun viewModelClass(): Class<BaseViewModel> = BaseViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mViewModel.mActivityNavigator?.addFragment(R.id.rl_root,ChatDetailFragment(),false)
    }
}