package com.example.gethub.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String?,

    @ColumnInfo(name = "type")
    var type: String?,
): Parcelable

