package com.example.dogbreedapp.di.repository_helper.database.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value= ["username"], unique = true)])
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String = "",
    val password: String = "",
    @ColumnInfo("full_name")
    val fullName: String = ""
)