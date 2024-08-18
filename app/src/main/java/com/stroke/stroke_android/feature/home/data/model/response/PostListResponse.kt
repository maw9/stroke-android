package com.stroke.stroke_android.feature.home.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: String?,

    @SerialName("created_at")
    val createdAt: String?,

    val width: Int?,

    val height: Int?,

    @SerialName("alt_description")
    val description: String?,

    val urls: URLsResponse?,

    val user: UserResponse?
)

@Serializable
data class URLsResponse(
    val raw: String?,
    val thumb: String?
)

@Serializable
data class UserResponse(
    val id: String?,
    val name: String?,

    @SerialName("profile_image")
    val profileImage: ProfileImageResponse?
)

@Serializable
data class ProfileImageResponse(
    val medium: String?
)
