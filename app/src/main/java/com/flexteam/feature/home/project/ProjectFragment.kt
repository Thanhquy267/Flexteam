package com.flexteam.feature.home.project

import androidx.recyclerview.widget.LinearLayoutManager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.OnProjectListener
import com.flexteam.adapter.ProjectAdapter
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentProjectBinding
import com.flexteam.feature.home.project.task.TaskFragment
import com.flexteam.model.ProjectModel

class ProjectFragment : BaseBindingModelFragment<FragmentProjectBinding, ProjectViewModel>(),
    OnProjectListener {
    override fun layoutId(): Int = R.layout.fragment_project
    override fun viewModelClass(): Class<ProjectViewModel> = ProjectViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        setUpRecyclerView()
    }

    override fun onProjectClick(project: ProjectModel?) {
        mViewModel.mActivityNavigator?.addFragment(R.id.rl_root, TaskFragment(), true)
    }

    private fun setUpRecyclerView() {
        mViewModel.dummyData()
        mViewModel.mProjectAdapter = ProjectAdapter(mViewModel.mListProject, this)
        mLayoutBinding.rvProjects.adapter = mViewModel.mProjectAdapter
        mLayoutBinding.rvProjects.layoutManager = LinearLayoutManager(context)
    }
}