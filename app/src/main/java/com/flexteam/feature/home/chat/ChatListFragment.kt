package com.flexteam.feature.home.chat

import androidx.recyclerview.widget.LinearLayoutManager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.ChatListAdapter
import com.flexteam.adapter.OnChatClickListener
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentChatListBinding
import com.flexteam.feature.home.chat.chatdetail.ChatDetailActivity
import com.flexteam.model.ChatListModel

class ChatListFragment : BaseBindingModelFragment<FragmentChatListBinding, ChatListViewModel>() ,OnChatClickListener{
    override fun layoutId(): Int = R.layout.fragment_chat_list
    override fun viewModelClass(): Class<ChatListViewModel> = ChatListViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        mViewModel.dummyData()
        setupRecyclerView()
    }

    override fun onChatClicked(item: ChatListModel?) {
        mViewModel.mActivityNavigator?.startActivity(ChatDetailActivity())
    }

    private fun setupRecyclerView() {
        mViewModel.mChatListAdapter = ChatListAdapter(mViewModel.mChatList,this)
        mLayoutBinding.rvListChat.adapter = mViewModel.mChatListAdapter
        mLayoutBinding.rvListChat.layoutManager = LinearLayoutManager(context)
    }

}