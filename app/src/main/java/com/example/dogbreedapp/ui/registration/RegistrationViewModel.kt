package com.example.dogbreedapp.ui.registration

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
class RegistrationViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    private val _userAdded: MutableLiveData<Boolean> = MutableLiveData()
    val userAdded: LiveData<Boolean> = _userAdded

    fun saveUserData(user: User){
        viewModelScope.launch {
            _userAdded.value = repository.insertUserIfNotExists(user)
        }
    }

}