package com.example.dogbreedapp.di.repository

import com.example.dogbreedapp.di.repository_helper.database.DatabaseHelper
import com.example.dogbreedapp.di.repository_helper.database.entites.User
import com.example.dogbreedapp.di.repository_helper.remote.RemoteHelper
import com.example.dogbreedapp.di.repository_helper.shared_prefs.SharedPrefsHelper
import com.example.dogbreedapp.models.DogBreedsResponse
import com.example.dogbreedapp.models.DogImgResponse
import com.example.dogbreedapp.models.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppDataManager @Inject constructor(
    private val mRemoteHelper: RemoteHelper,
    private val mSharedPrefsHelper: SharedPrefsHelper,
    private val mDatabaseHelper: DatabaseHelper
): DataRepository {

    override suspend fun getDogBreeds(): Flow<Result<DogBreedsResponse>> {
        return mRemoteHelper.getDogBreeds()
    }

    override suspend fun getDogImg(referenceImgId: String): Flow<Result<DogImgResponse>> {
        return mRemoteHelper.getDogImg(referenceImgId)
    }

    override fun saveUserLoggedIn(loggedIn: Boolean) {
        mSharedPrefsHelper.saveUserLoggedIn(loggedIn)
    }

    override fun getUserLoggedIn(): Boolean? {
        return mSharedPrefsHelper.getUserLoggedIn()
    }

    override suspend fun insertUserIfNotExists(user: User): Boolean {
        return mDatabaseHelper.insertUserIfNotExists(user)
    }

    override suspend fun getUserByUsernamePassword(username: String, password: String): List<User>? {
        return mDatabaseHelper.getUserByUsernamePassword(username, password)
    }

    override suspend fun doesUsernameExist(username: String): Boolean {
        return mDatabaseHelper.doesUsernameExist(username)
    }


}