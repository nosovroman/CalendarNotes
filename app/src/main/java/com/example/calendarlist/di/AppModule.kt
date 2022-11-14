package com.example.calendarlist.di

import android.content.Context
import com.example.calendarlist.data.repository.TaskRepositoryImpl
import com.example.calendarlist.data.room.dao.TaskDao
import com.example.calendarlist.data.room.database.CalendarDatabase
import com.example.calendarlist.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): CalendarDatabase {
        return CalendarDatabase.getDBInstance(context)
    }

    @Provides
    @Singleton
    fun provideTaskDao(calendarDB: CalendarDatabase): TaskDao {
        return calendarDB.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}