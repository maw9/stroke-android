package com.stroke.stroke_android.feature.profile.ui.model

import com.stroke.stroke_android.feature.profile.data.model.request.ProfileRequest

data class UserProfile(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val photoUrl: String?,
    val gender: Gender,
    val description: String
)

fun UserProfile.asProfileRequest(): ProfileRequest {
    return ProfileRequest(
        id = id,
        name = name,
        phone = phone,
        email = email,
        photoUrl = photoUrl?.formatPhotoUrl(),
        gender = gender.value,
        description = description
    )
}

fun String.formatPhotoUrl(): String {
    return if (this.startsWith("/"))
        "https://lh3.googleusercontent.com$this"
    else
        this
}