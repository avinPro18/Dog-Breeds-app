package com.example.dogbreedapp.di.repository_helper.shared_prefs

interface SharedPrefsHelper {

    fun saveUserLoggedIn(loggedIn: Boolean)
    fun getUserLoggedIn(): Boolean?

}