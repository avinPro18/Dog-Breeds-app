package com.example.dogbreedapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.di.repository.DataRepository
import com.example.dogbreedapp.di.repository_helper.database.entites.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    private val _user: MutableLiveData<User?> = MutableLiveData()
    val user: LiveData<User?> = _user

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    init {
        _isLoggedIn.value = repository.getUserLoggedIn() == true
    }

    fun getUserByUsernamePassword(username: String, password: String){
        viewModelScope.launch {
            val users = repository.getUserByUsernamePassword(username, password)
            Log.d("Test","user: $users")
            if(users?.isNotEmpty() == true){
                _user.value = users[0]
                repository.saveUserLoggedIn(true)
            }else{
                _error.value = "User not found"
            }
        }
    }
}