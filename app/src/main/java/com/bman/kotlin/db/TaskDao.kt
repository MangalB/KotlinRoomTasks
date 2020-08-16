package com.bman.kotlin.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("select * from task where isDone=0")
    fun getAllUndoneTasks(): LiveData<List<TaskEntity>>

    @Query("select * from task where isDone=1")
    fun getAllDoneTasks(): LiveData<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskEntity)

    @Update
    suspend fun updateTaskDone(task: TaskEntity)

}