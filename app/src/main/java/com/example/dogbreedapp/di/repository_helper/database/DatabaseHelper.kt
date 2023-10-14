package com.example.dogbreedapp.di.repository_helper.database

import com.example.dogbreedapp.di.repository_helper.database.entites.User
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {
    suspend fun insertUserIfNotExists(user: User): Boolean
    suspend fun getUserByUsernamePassword(username: String, password: String): List<User>?
    suspend fun doesUsernameExist(username: String): Boolean
}