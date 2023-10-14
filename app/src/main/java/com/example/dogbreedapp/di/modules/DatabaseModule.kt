package com.example.dogbreedapp.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.example.dogbreedapp.di.repository_helper.database.DatabaseHelper
import com.example.dogbreedapp.di.repository_helper.database.DatabaseRepo
import com.example.dogbreedapp.di.repository_helper.database.dao.UserDao
import com.example.dogbreedapp.di.repository_helper.database.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "My-DB")
            .build()
    }

    @Provides
    @Singleton
    fun providesUserDao(appDatabase: AppDatabase): UserDao{
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseHelper(
        userDao: UserDao
    ): DatabaseHelper {
        return DatabaseRepo(userDao)
    }
}