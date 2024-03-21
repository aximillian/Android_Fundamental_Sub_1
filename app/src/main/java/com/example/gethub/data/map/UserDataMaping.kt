package com.example.gethub.data.map

import com.example.gethub.data.remote.response.DetailResponse
import com.example.gethub.data.remote.response.RepositoryResponse
import com.example.gethub.data.remote.response.UserResponse
import com.example.gethub.domain.model.DetailUserModel
import com.example.gethub.domain.model.RepositoryModel
import com.example.gethub.domain.model.UserModel

object UserDataMaping {

    fun mapUserResponse(userResponse: UserResponse): UserModel{
        val (username, avatarUrl, type) = userResponse
        return UserModel(
            username = username,
            avatarUrl = avatarUrl,
            type = type,
        )
    }

    fun mapDetailResponse(userResponse: DetailResponse): DetailUserModel{
        return DetailUserModel(
            username = userResponse.username,
            avatarUrl = userResponse.avatarUrl,
            type = userResponse.type,
            name = userResponse.name,
            company = userResponse.company,
            blog = userResponse.blog,
            location = userResponse.location,
            bio = userResponse.bio,
            publicRepos = userResponse.publicRepos,
            followers = userResponse.followers,
            following = userResponse.following,
        )
    }

    fun mapReposResponse(userResponse: RepositoryResponse): RepositoryModel {
        return RepositoryModel(
            id = userResponse.id,
            name = userResponse.name,
            topics = userResponse.topics,
            description = userResponse.description,
            language = userResponse.language,
            updatedAt = userResponse.updatedAt,
            forksCount = userResponse.forksCount,
            stargazersCount = userResponse.stargazersCount,
        )
    }
}