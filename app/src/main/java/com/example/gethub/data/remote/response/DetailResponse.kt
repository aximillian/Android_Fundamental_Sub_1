package com.example.gethub.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailResponse(

    @field:SerializedName("login")
    val username: String?,

    @field:SerializedName("avatar_url")
    val avatarUrl: String?,

    @field:SerializedName("type")
    var type: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("company")
    val company: String?,

    @field:SerializedName("blog")
    val blog: String?,

    @field:SerializedName("location")
    val location: String?,

    @field:SerializedName("bio")
    val bio: String?,

    @field:SerializedName("public_repos")
    val publicRepos: Int?,

    @field:SerializedName("followers")
    val followers: Int?,

    @field:SerializedName("following")
    val following: Int?,
) :Parcelable
