package com.example.dogbreedapp.di.repository_helper.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogbreedapp.di.repository_helper.database.dao.UserDao
import com.example.dogbreedapp.di.repository_helper.database.entites.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}