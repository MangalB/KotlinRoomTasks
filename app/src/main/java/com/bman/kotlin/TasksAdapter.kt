package com.bman.kotlin

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bman.kotlin.R
import com.bman.kotlin.db.TaskEntity
import java.text.SimpleDateFormat

class TasksAdapter(private val context:Context, private val isDone:Boolean, private val taskInteraction: TaskInteraction): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var tasks: List<TaskEntity>? = null

    fun updateTasks(tasks: List<TaskEntity>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_task, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks?.size?:0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        holder.cbTask.isChecked = isDone
        if (isDone) {
            holder.cbTask.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
        } else {
            holder.cbTask.setTextColor(Color.BLACK)
        }
//        holder.cbTask.text = tasks?.get(position)?.task
//        holder.tvTime.text = tasks?.get(position)
        tasks?.get(position)?.apply {
            holder.cbTask.text = this.task
            holder.tvTime.text = SimpleDateFormat("dd MMM HH:mm").format(this.time)
        }
    }


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbTask: CheckBox = itemView.findViewById(R.id.cb_task)
        val tvTime: TextView = itemView.findViewById(R.id.tv_task_time)

        init {
            this.setIsRecyclable(false)
            cbTask.setOnCheckedChangeListener { button, b ->
                val item: TaskEntity = tasks!!.get(adapterPosition).apply { isDone = !isDone }
                taskInteraction.onTaskStateChanged(item)
            }
        }
    }

    interface TaskInteraction {
        fun onTaskStateChanged(task: TaskEntity)
    }
}