package com.flexteam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.R
import com.flexteam.databinding.LayoutItemTaskBinding
import com.flexteam.model.TaskModel

class TaskAdapter(private val listTasks : ArrayList<TaskModel>) : RecyclerView.Adapter<TaskViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_item_task,parent,false))
    }
    override fun getItemCount(): Int = listTasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
      return holder.bind(listTasks.get(index = position))
    }
}

class TaskViewHolder(private val binder : LayoutItemTaskBinding) : RecyclerView.ViewHolder(binder.root) {
  fun bind(task : TaskModel){
      binder.tvTitle.text = task.title
      binder.tvTag.text = task.tag
  }
}
