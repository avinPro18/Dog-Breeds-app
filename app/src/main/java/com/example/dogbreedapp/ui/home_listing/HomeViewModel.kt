package com.example.dogbreedapp.ui.home_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.di.repository.DataRepository
import com.example.dogbreedapp.models.DogBreedsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.dogbreedapp.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    private val _dogBreeds: MutableLiveData<DogBreedsResponse> = MutableLiveData()
    val dogBreeds: LiveData<DogBreedsResponse> = _dogBreeds

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    init{
        fetchDogBreeds()
    }

    private fun fetchDogBreeds(){
        viewModelScope.launch {
            repository.getDogBreeds()
                .flowOn(Dispatchers.IO)
                .catch {
                    _error.value = it.localizedMessage
                }
                .collect{
                    when(it){
                        is Result.Failure -> _error.value = it.errorMsg
                        is Result.Loading -> _isLoading.value = it.loading
                        is Result.Success -> {
                            it.data?.forEach {breedData ->
                                breedData.url = fetchDogImg(breedData.reference_image_id ?: "")
                            }
                            _dogBreeds.value = it.data
                        }
                    }
                }
        }
    }

    private suspend fun fetchDogImg(referenceImgId: String): String{
        var url = ""
        repository.getDogImg(referenceImgId)
            .collect{
                if(it is Result.Success){
                     url = it.data?.url ?: ""
                    return@collect
                }
            }
        return url
    }

    fun logout(complete: () -> Unit){
        repository.saveUserLoggedIn(false)
        complete()
    }

}