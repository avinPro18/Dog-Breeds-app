package com.example.dogbreedapp.di.repository_helper.remote

import com.example.dogbreedapp.models.DogBreedsResponse
import com.example.dogbreedapp.models.DogImgResponse
import com.example.dogbreedapp.utils.DOG_BREEDS_ENDPOINT
import com.example.dogbreedapp.utils.DOG_IMG_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {
    @GET(DOG_BREEDS_ENDPOINT)
    suspend fun getDogBreeds(@Query("limit") limit: String = "20", @Query("page") page: String = "0"): Response<DogBreedsResponse>

    @GET("$DOG_IMG_ENDPOINT/{reference_img_id}")
    suspend fun getDogImg(@Path("reference_img_id") referenceImgId: String): Response<DogImgResponse>
}