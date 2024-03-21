package com.example.gethub.domain.repository

import androidx.lifecycle.liveData
import com.example.gethub.data.remote.response.ResponseApi

class ImplementUserRepository(private val remoteSource: com.example.gethub.data.remote.RemoteSource) : UserRepository {

    override fun getSearch(query: String) = liveData{
        emit(ResponseApi.Loading())
        try {
            val response = remoteSource.getSearch(query)
            val result = response.items.map { userResponse ->
                com.example.gethub.data.map.UserDataMaping.mapUserResponse(userResponse)
            }
            emit(ResponseApi.Success(result))
        } catch (e: Exception) {
            emit(ResponseApi.Error(e.message))
        }
    }

    override fun getDetailUser(username: String) = liveData{
        emit(ResponseApi.Loading())
        try {
            val response = remoteSource.getDetailUser(username)
            val result = com.example.gethub.data.map.UserDataMaping.mapDetailResponse(response)
            emit(ResponseApi.Success(result))
        } catch (e: Exception) {
            emit(ResponseApi.Error(e.message))
        }
    }

    override fun getRepository(username: String) = liveData{
        emit(ResponseApi.Loading())
        try {
            val response = remoteSource.getRepository(username)
            val result = response.map { repoResponse ->
                com.example.gethub.data.map.UserDataMaping.mapReposResponse(repoResponse)
            }
            emit(ResponseApi.Success(result))
        } catch (e: Exception) {
            emit(ResponseApi.Error(e.message))
        }
    }

    override fun getFollowers(username: String) = liveData{
        emit(ResponseApi.Loading())
        try {
            val response = remoteSource.getFollowers(username)
            val result = response.map { userResponse ->
                com.example.gethub.data.map.UserDataMaping.mapUserResponse(userResponse)
            }
            emit(ResponseApi.Success(result))
        } catch (e: Exception) {
            emit(ResponseApi.Error(e.message))
        }
    }

    override fun getFollowing(username: String) = liveData{
        emit(ResponseApi.Loading())
        try {
            val response = remoteSource.getFollowing(username)
            val result = response.map { userResponse ->
                com.example.gethub.data.map.UserDataMaping.mapUserResponse(userResponse)
            }
            emit(ResponseApi.Success(result))
        } catch (e: Exception) {
            emit(ResponseApi.Error(e.message))
        }
    }

    override fun getRandomUsers() = liveData{
        emit(ResponseApi.Loading())
        try {
            val response = remoteSource.getRandomUsers()
            val result = response.map { userResponse ->
                com.example.gethub.data.map.UserDataMaping.mapUserResponse(userResponse)
            }
            emit(ResponseApi.Success(result))
        } catch (e: Exception) {
            emit(ResponseApi.Error(e.message))
        }
    }

    companion object {
        @Volatile
        private var instance: ImplementUserRepository? = null

        fun getInstance(remoteSource: com.example.gethub.data.remote.RemoteSource): ImplementUserRepository =
            instance ?: synchronized(this) {
                instance ?: ImplementUserRepository(remoteSource).also { instance = it }
            }
    }
}


