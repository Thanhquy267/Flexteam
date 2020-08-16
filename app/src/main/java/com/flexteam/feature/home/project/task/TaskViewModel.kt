package com.flexteam.feature.home.project.task

import com.flexteam.adapter.TaskAdapter
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.model.TaskModel
import io.reactivex.functions.Consumer

class TaskViewModel : BaseViewModel() {
    var mListTasks = ArrayList<TaskModel>()
    var mTaskAdapter: TaskAdapter? = null
    var mActionBarViewModel = ActionBarViewModel()

    override fun onReady() {
        super.onReady()
        setupActionBar()
    }

    private fun setupActionBar(){
        mActionBarViewModel.mTitle.set("Tasks")
        mActionBarViewModel.mShouldShowStartIcon.set(true)
        mActionBarViewModel.mShouldShowEndIcon.set(true)
        mActionBarViewModel.mStartIconClickConsumer = Consumer {
            mActivityNavigator?.popFragmentBack()
        }
    }

    fun dummyData() {
        mListTasks.add(TaskModel().apply {
            title = "Login/Register Screen"
            tag = "Login"
        })
        mListTasks.add(TaskModel().apply {
            title = "[Flexteam] Handle UI for login screen  Handle UI for login screen  Handle UI for login screen "
            tag = "Login"
        })
    }
}