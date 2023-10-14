package com.example.dogbreedapp.di.repository_helper.remote

import android.content.Context
import com.example.dogbreedapp.models.DogBreedsDataItem
import com.example.dogbreedapp.models.DogBreedsResponse
import com.example.dogbreedapp.models.DogImgResponse
import com.example.dogbreedapp.utils.toResultFlow
import javax.inject.Inject
import com.example.dogbreedapp.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteRepo @Inject constructor(
    private val context: Context,
    private val mRetrofitApi: RetrofitApi
): RemoteHelper{

    override suspend fun getDogBreeds(): Flow<Result<DogBreedsResponse>> {
        return context.toResultFlow {
            mRetrofitApi.getDogBreeds()
        }
    }

    override suspend fun getDogImg(referenceImgId: String): Flow<Result<DogImgResponse>> {
        return context.toResultFlow {
            mRetrofitApi.getDogImg(referenceImgId)
        }
    }

}