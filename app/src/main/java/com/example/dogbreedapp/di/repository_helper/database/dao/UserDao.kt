package com.example.dogbreedapp.di.repository_helper.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogbreedapp.di.repository_helper.database.entites.User

@androidx.room.Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * from user where username = :username AND password = :password")
    suspend fun getUserByUsernamePassword(username: String, password: String): List<User>?

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE username = :username)")
    suspend fun doesUsernameExist(username: String): Boolean
}