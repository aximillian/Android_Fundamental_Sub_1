package com.example.gethub.data.di

import com.example.gethub.data.remote.RemoteSource
import com.example.gethub.data.remote.api.ApiConfig

object RemoteDataSourceProvider {

    fun remoteDataSourceProvider(): com.example.gethub.data.remote.RemoteSource {
        val apiService = ApiConfig.getApiService()
        return com.example.gethub.data.remote.RemoteSource.getInstance(apiService)
    }
}