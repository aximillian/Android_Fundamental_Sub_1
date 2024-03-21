package com.example.gethub.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean?,

    @field:SerializedName("items")
    val items: List<UserResponse>
) :Parcelable
