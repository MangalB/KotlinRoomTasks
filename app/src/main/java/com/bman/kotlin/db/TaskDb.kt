package com.bman.kotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(RoomConverters::class)
@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDb : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANTCE: TaskDb? = null

        fun getDatabase(context: Context): TaskDb {
            val temp = INSTANTCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDb::class.java,
                    "taskRoom"
                ).build()
                INSTANTCE = instance
                return instance
            }
        }
    }
}