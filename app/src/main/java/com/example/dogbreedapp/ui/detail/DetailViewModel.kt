package com.example.dogbreedapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.dogbreedapp.di.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {



}