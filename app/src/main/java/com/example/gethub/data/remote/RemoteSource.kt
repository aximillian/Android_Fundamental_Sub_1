package com.example.gethub.data.remote

import com.example.gethub.data.remote.api.ApiService

class RemoteSource(private val apiService: ApiService) {

    suspend fun getSearch(query: String) = apiService.getSearch(query)

    suspend fun getDetailUser(username: String) = apiService.getDetailUser(username)

    suspend fun getRepository(username: String) = apiService.getRepository(username)

    suspend fun getFollowers(username: String) = apiService.getFollowers(username)

    suspend fun getFollowing(username: String) = apiService.getFollowing(username)

    suspend fun getRandomUsers() = apiService.getRandomUsers()

    companion object {
        @Volatile
        private var instance: com.example.gethub.data.remote.RemoteSource? = null

        fun getInstance(apiService: ApiService): com.example.gethub.data.remote.RemoteSource =
            com.example.gethub.data.remote.RemoteSource.Companion.instance ?: synchronized(this) {
                com.example.gethub.data.remote.RemoteSource.Companion.instance
                    ?: com.example.gethub.data.remote.RemoteSource(apiService)
            }.also {
                com.example.gethub.data.remote.RemoteSource.Companion.instance = it
            }
    }
}