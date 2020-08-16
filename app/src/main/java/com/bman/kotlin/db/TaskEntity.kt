package com.bman.kotlin.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val task: String,
    val time: Date,
    var timeDone: Date?,
    var isDone: Boolean
) {
    constructor(task: String) : this(null, task, Date(), null, false)
}
