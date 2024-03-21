package com.example.gethub.domain.repository

import androidx.lifecycle.LiveData
import com.example.gethub.data.remote.response.ResponseApi
import com.example.gethub.domain.model.DetailUserModel
import com.example.gethub.domain.model.RepositoryModel
import com.example.gethub.domain.model.UserModel


interface UserRepository {

    fun getSearch(query: String): LiveData<ResponseApi<List<UserModel>>>

    fun getDetailUser(username: String): LiveData<ResponseApi<DetailUserModel>>

    fun getRepository(username: String): LiveData<ResponseApi<List<RepositoryModel>>>

    fun getFollowers(username: String): LiveData<ResponseApi<List<UserModel>>>

    fun getFollowing(username: String): LiveData<ResponseApi<List<UserModel>>>

    fun getRandomUsers(): LiveData<ResponseApi<List<UserModel>>>
}