package com.example.gethub.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(

    @field:SerializedName("login")
    val username: String?,

    @field:SerializedName("avatar_url")
    val avatarUrl: String?,

    @field:SerializedName("type")
    val type: String?,

): Parcelable