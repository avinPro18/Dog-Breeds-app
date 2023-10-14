package com.example.dogbreedapp.di.repository_helper.remote

import com.example.dogbreedapp.models.DogBreedsResponse
import com.example.dogbreedapp.models.DogImgResponse
import kotlinx.coroutines.flow.Flow
import com.example.dogbreedapp.models.Result

interface RemoteHelper {
    suspend fun getDogBreeds(): Flow<Result<DogBreedsResponse>>
    suspend fun getDogImg(referenceImgId: String): Flow<Result<DogImgResponse>>
}