package com.flexteam.feature.home.project.task

import androidx.recyclerview.widget.LinearLayoutManager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.TaskAdapter
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentTaskBinding

class TaskFragment : BaseBindingModelFragment<FragmentTaskBinding,TaskViewModel>(){
    override fun layoutId(): Int = R.layout.fragment_task
    override fun viewModelClass(): Class<TaskViewModel> = TaskViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        setUpRecyclerView()
        mLayoutBinding.layoutActionBar.viewModel = mViewModel.mActionBarViewModel
    }

    private fun setUpRecyclerView(){
        mViewModel.dummyData()
        mViewModel.mTaskAdapter = TaskAdapter(mViewModel.mListTasks)
        mLayoutBinding.rvTasks.adapter = mViewModel.mTaskAdapter
        mLayoutBinding.rvTasks.layoutManager = LinearLayoutManager(context)
    }
}