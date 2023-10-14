package com.example.dogbreedapp.di.repository_helper.database

import com.example.dogbreedapp.di.repository_helper.database.dao.UserDao
import com.example.dogbreedapp.di.repository_helper.database.entites.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepo @Inject constructor(
    private val userDao: UserDao
): DatabaseHelper {

    override suspend fun insertUserIfNotExists(user: User): Boolean {
        return if(doesUsernameExist(user.username)){
            false
        }else{
            userDao.insert(user)
            true
        }
    }

    override suspend fun getUserByUsernamePassword(username: String, password: String): List<User>? {
        return userDao.getUserByUsernamePassword(username, password)
    }

    override suspend fun doesUsernameExist(username: String): Boolean {
        return userDao.doesUsernameExist(username)
    }
}