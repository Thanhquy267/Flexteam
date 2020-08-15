package com.flexteam.feature.notification

import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentNotificationBinding
import com.flexteam.model.NotificationModel

class NotificationFragment :
    BaseBindingModelFragment<FragmentNotificationBinding, NotificationViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_notification
    override fun viewModelClass(): Class<NotificationViewModel> = NotificationViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        initRecyclerView()
        initData()
    }

    private fun initRecyclerView() {
        mViewModel.mNotificationAdapter = NotificationAdapter(mViewModel.mListNotification)
        mLayoutBinding.rvNotification.adapter = mViewModel.mNotificationAdapter
    }

    private fun initData() {
//        mViewModel.mListNotification.add(notification)
//        mViewModel.mNotificationAdapter?.setData(mViewModel.mListNotification)
    }

    companion object {
        fun newInstance(): NotificationFragment {
            return NotificationFragment()
        }
    }
}