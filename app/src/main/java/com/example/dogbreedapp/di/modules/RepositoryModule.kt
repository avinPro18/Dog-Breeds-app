package com.example.dogbreedapp.di.modules

import com.example.dogbreedapp.di.repository.AppDataManager
import com.example.dogbreedapp.di.repository.DataRepository
import com.example.dogbreedapp.di.repository_helper.database.DatabaseHelper
import com.example.dogbreedapp.di.repository_helper.remote.RemoteHelper
import com.example.dogbreedapp.di.repository_helper.shared_prefs.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(
        remoteHelper: RemoteHelper,
        sharedPrefsHelper: SharedPrefsHelper,
        databaseHelper: DatabaseHelper
    ): DataRepository {
        return AppDataManager(remoteHelper, sharedPrefsHelper, databaseHelper)
    }

}