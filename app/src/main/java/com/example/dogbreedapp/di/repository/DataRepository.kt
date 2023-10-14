package com.example.dogbreedapp.di.repository

import com.example.dogbreedapp.di.repository_helper.database.DatabaseHelper
import com.example.dogbreedapp.di.repository_helper.database.dao.UserDao
import com.example.dogbreedapp.di.repository_helper.remote.RemoteHelper
import com.example.dogbreedapp.di.repository_helper.shared_prefs.SharedPrefsHelper

interface DataRepository: RemoteHelper, SharedPrefsHelper, DatabaseHelper