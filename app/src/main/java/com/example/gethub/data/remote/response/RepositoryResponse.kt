package com.example.gethub.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryResponse(

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("topics")
    val topics: List<String>?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("language")
    val language: String?,

    @field:SerializedName("updated_at")
    val updatedAt: String?,

    @field:SerializedName("forks_count")
    val forksCount: Int?,

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int?,
):  Parcelable
