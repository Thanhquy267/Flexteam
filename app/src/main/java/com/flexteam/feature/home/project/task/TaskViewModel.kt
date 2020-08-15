package com.flexteam.feature.home.project.task

import com.flexteam.adapter.TaskAdapter
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.model.TaskModel

class TaskViewModel : BaseViewModel() {
    var mListTasks = ArrayList<TaskModel>()
    var mTaskAdapter: TaskAdapter? = null
    var mActionBarViewModel = ActionBarViewModel()

    override fun onReady() {
        super.onReady()
        setupActionBar()
    }

    fun setupActionBar(){
        mActionBarViewModel.mTitle.set("Tasks")
        mActionBarViewModel.mShouldShowStartIcon.set(true)
        mActionBarViewModel.mShouldShowEndIcon.set(true)
    }

    fun dummyData() {
        mListTasks.add(TaskModel().apply {
            title = "Login/Register Screen"
            description = "Handle UI/Logic/API"
        })
        mListTasks.add(TaskModel().apply {
            title = "Home Screen"
            description = "Add Viewpager/Handle animation"
        })
    }
}