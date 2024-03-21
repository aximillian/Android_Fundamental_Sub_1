package com.example.gethub.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryModel(

    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "topics")
    val topics: List<String>?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "language")
    val language: String?,

    @ColumnInfo(name = "updatedAt")
    val updatedAt: String?,

    @ColumnInfo(name = "forksCount")
    val forksCount: Int?,

    @ColumnInfo(name = "stargazersCount")
    val stargazersCount: Int?,
): Parcelable
