package com.bman.kotlin

import com.bman.kotlin.db.TaskDao
import com.bman.kotlin.db.TaskEntity

class TaskRepo(private val taskDao: TaskDao) {

    fun getAllUndoeTasks() = taskDao.getAllUndoneTasks()

    fun getAllDoneTasks() = taskDao.getAllDoneTasks()

    suspend fun addTask(task: TaskEntity) = taskDao.addTask(task)

    suspend fun updateTask(task: TaskEntity) = taskDao.updateTaskDone(task)
}