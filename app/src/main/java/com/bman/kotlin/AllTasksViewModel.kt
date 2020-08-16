package com.bman.kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bman.kotlin.db.TaskDb
import com.bman.kotlin.db.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllTasksViewModel(application: Application) : AndroidViewModel(application) {

    val repo: TaskRepo
    private val doneTasks: LiveData<List<TaskEntity>>
    private val undoneTasks: LiveData<List<TaskEntity>>

    init {
        val taskDao = TaskDb.getDatabase(application).taskDao()
        repo = TaskRepo(taskDao)
        doneTasks = repo.getAllDoneTasks()
        undoneTasks = repo.getAllUndoeTasks()
    }

    fun getTasks(isdone: Boolean) = if(isdone) doneTasks else undoneTasks

    fun addTask(task: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTask(TaskEntity(task))
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTask(task)
        }
    }
}