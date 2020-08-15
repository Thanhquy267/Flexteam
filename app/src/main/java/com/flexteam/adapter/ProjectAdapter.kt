package com.flexteam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.R
import com.flexteam.databinding.LayoutItemProjectBinding
import com.flexteam.model.ProjectModel

interface OnProjectListener{
    fun onProjectClick(project: ProjectModel?)
}
class ProjectAdapter(private val listProject : ArrayList<ProjectModel>?,val listener: OnProjectListener) : RecyclerView.Adapter<ProjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_item_project,parent,false),listener)
    }

    override fun getItemCount(): Int {
        return  listProject?.size?:0
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
      holder.bind(listProject?.get(position))
    }
}

class ProjectViewHolder(private val binder : LayoutItemProjectBinding,val listener: OnProjectListener) : RecyclerView.ViewHolder(binder.root) {
      fun bind(project : ProjectModel?){
          val memberCount = "${project?.members} members"
          binder.tvProjectName.text = project?.title
          binder.tvMemberCount.text = memberCount
          binder.llRoot.setOnClickListener {
              listener.onProjectClick(project)
          }
      }
}
