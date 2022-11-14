package com.example.calendarlist.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calendarlist.common.Constants
import com.example.calendarlist.data.room.dao.TaskDao
import com.example.calendarlist.data.room.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class CalendarDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE_DB: CalendarDatabase? = null
        fun getDBInstance(context: Context): CalendarDatabase {
            synchronized(this) {
                var instance = INSTANCE_DB
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalendarDatabase::class.java,
                        Constants.DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE_DB = instance
                }
                return instance
            }
        }
    }
}