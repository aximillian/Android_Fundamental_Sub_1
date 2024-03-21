package com.example.gethub.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUserModel(

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String?,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "name")
    var name:String?,

    @ColumnInfo(name = "company")
    var company: String?,

    @ColumnInfo(name = "blog")
    var blog: String?,

    @ColumnInfo(name = "location")
    var location: String?,

    @ColumnInfo(name = "bio")
    var bio: String?,

    @ColumnInfo(name = "publicRepos")
    var publicRepos: Int?,

    @ColumnInfo(name = "followers")
    var followers: Int?,

    @ColumnInfo(name = "following")
    var following: Int?,
): Parcelable
