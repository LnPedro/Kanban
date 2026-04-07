package com.pedro.kanban.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pedro.kanban.R
import com.pedro.kanban.data.model.Status
import com.pedro.kanban.data.model.Task
import com.pedro.kanban.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>,
    private val taskSelected: (Task, Int) -> Unit

): ListAdapter<Task, TaskAdapter.MyViewHolder>(DIFF_CALLBACK){


    companion object {

        val SELECT_BACK: Int = 1
        val SELECT_REMOVER: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5


        private val DIFF_CALLBACK = object : DiffUtil.itemCallBack<Task>(){

            override fun areItemsTheSame(
                oldItem: Task,
                newItem: Task
            ): Boolean{
                return oldItem.id == newItem.id && oldItem.description == newItem.description
            }
            override fun areConstentsTheSame(
                oldItem: Task,
                newItem: Task
            ): Boolean{
                return oldItem == newItem && oldItem.description == newItem.description
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.textDescription.text = task.description
        setIndicators(task, holder)

    }

    private fun setIndicators(task: Task, holder: MyViewHolder){

        when (task.status){

            Status.TODO -> {
                holder.binding.backButton.isVisible = false
                holder.binding.forwardButton.setColorFilter(ContextCompat.getColor(context, R.color.color_status_done))
                holder.binding.forwardButton.setOnClickListener { taskSelected(task, SELECT_NEXT) }

            }
            Status.DOING -> {
                holder.binding.backButton.setColorFilter(ContextCompat.getColor(context, R.color.color_status_todo ))
                holder.binding.forwardButton.setColorFilter(ContextCompat.getColor(context, R.color.color_status_done))
                holder.binding.forwardButton.setOnClickListener { taskSelected(task, SELECT_NEXT) }
                holder.binding.backButton.setOnClickListener { taskSelected(task, SELECT_BACK) }
            }
            Status.DONE -> {
                holder.binding.forwardButton.isVisible = false
                holder.binding.backButton.setColorFilter(ContextCompat.getColor(context, R.color.color_status_todo ))
                holder.binding.backButton.setOnClickListener { taskSelected(task, SELECT_BACK) }


            }
        }
        holder.binding.buttonEditar.setOnClickListener { taskSelected(task, SELECT_EDIT)}
        holder.binding.buttonDetails.setOnClickListener { taskSelected(task, SELECT_DETAILS)}
        holder.binding.buttonDelete.setOnClickListener { taskSelected(task, SELECT_REMOVER)}



    }

    override fun getItemCount() = taskList.size
    inner class MyViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){

    }
}