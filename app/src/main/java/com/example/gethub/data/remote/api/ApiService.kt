package com.example.gethub.data.remote.api

import com.example.gethub.data.remote.response.DetailResponse
import com.example.gethub.data.remote.response.RepositoryResponse
import com.example.gethub.data.remote.response.SearchResponse
import com.example.gethub.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    suspend fun getSearch(@Query("q") query: String): SearchResponse

    @GET("/users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): DetailResponse

    @GET("/users/{username}/repos")
    suspend fun getRepository(@Path("username") username: String): List<RepositoryResponse>

    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<UserResponse>

    @GET("/users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<UserResponse>

    @GET("/users/random")
    suspend fun getRandomUsers(): List<UserResponse>
}