package com.flexteam.feature.home.project

import com.flexteam.adapter.ProjectAdapter
import com.flexteam.base.BaseViewModel
import com.flexteam.model.ProjectModel

class ProjectViewModel : BaseViewModel(){
    var mProjectAdapter : ProjectAdapter? = null
    var mListProject = ArrayList<ProjectModel>()

    fun dummyData(){
        mListProject.add(ProjectModel().apply {
            title = "Flexteam"
            members = 5
        })
        mListProject.add(ProjectModel().apply {
            title = "Flexgroup"
            members = 3
        })
    }
}