package com.flexteam.feature.tasknotification

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentTaskNotificationBinding

class TaskNotificationFragment: BaseBindingModelFragment<FragmentTaskNotificationBinding, TaskNotificationViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_task_notification
    override fun viewModelClass(): Class<TaskNotificationViewModel> = TaskNotificationViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel
}