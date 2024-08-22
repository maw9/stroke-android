package com.stroke.stroke_android.feature.auth.domain.model

import com.stroke.stroke_android.feature.profile.ui.model.Gender
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

data class AuthUser(
    val id: String,
    val name: String?,
    val email: String?,
    val phone: String?,
    val profileUrl: String?
)

fun AuthUser.asUserProfile(): UserProfile {
    return UserProfile(
        id = id,
        name = name.orEmpty(),
        phone = phone.orEmpty(),
        email = email.orEmpty(),
        photoUrl = profileUrl,
        gender = Gender.Male,
        description = ""
    )
}
