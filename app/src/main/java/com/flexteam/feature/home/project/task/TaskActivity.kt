package com.flexteam.feature.home.project.task

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.base.BaseViewModel
import com.flexteam.databinding.ActivityTaskBinding

class TaskActivity : BaseBindingModelActivity<ActivityTaskBinding, BaseViewModel>() {
    override fun layoutId(): Int = R.layout.activity_task
    override fun viewModelClass(): Class<BaseViewModel> = BaseViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mViewModel.mActivityNavigator?.addFragment(R.id.rl_root,TaskFragment(),false)
    }
}