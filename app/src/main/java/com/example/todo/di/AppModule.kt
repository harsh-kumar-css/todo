package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.data.local.TodoDatabase
import com.example.todo.data.remote.TodoRepositoryImpl
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.util.AlarmManager.AlarmScheduler
import com.example.todo.util.AlarmManager.AlarmSchedulerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app : Application) : TodoDatabase {
        return Room.databaseBuilder(app,TodoDatabase::class.java,"todo_database").build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db : TodoDatabase) : TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideAlarmScheduler(app: Application) : AlarmScheduler{
        return AlarmSchedulerImpl(app)
    }

}