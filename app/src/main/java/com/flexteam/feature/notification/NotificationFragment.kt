package com.flexteam.feature.notification

import androidx.recyclerview.widget.LinearLayoutManager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentNotificationBinding

class NotificationFragment :
    BaseBindingModelFragment<FragmentNotificationBinding, NotificationViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_notification
    override fun viewModelClass(): Class<NotificationViewModel> = NotificationViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        mLayoutBinding.layoutActionBar.viewModel = mViewModel.mActionBarViewModel
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mViewModel.dummyData()
        mViewModel.mNotificationAdapter = NotificationAdapter(mViewModel.mListNotification)
        mLayoutBinding.rvNotification.adapter = mViewModel.mNotificationAdapter
        mLayoutBinding.rvNotification.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance(): NotificationFragment {
            return NotificationFragment()
        }
    }
}