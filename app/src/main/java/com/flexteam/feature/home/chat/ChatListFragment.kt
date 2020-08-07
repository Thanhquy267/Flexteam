package com.flexteam.feature.home.chat

import androidx.recyclerview.widget.LinearLayoutManager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.ChatListAdapter
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentChatListBinding

class ChatListFragment : BaseBindingModelFragment<FragmentChatListBinding, ChatListViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_chat_list
    override fun viewModelClass(): Class<ChatListViewModel> = ChatListViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        mViewModel.dummyData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mViewModel.mChatListAdapter = ChatListAdapter(mViewModel.mChatList)
        mLayoutBinding.rvListChat.adapter = mViewModel.mChatListAdapter
        mLayoutBinding.rvListChat.layoutManager = LinearLayoutManager(context)
    }
}