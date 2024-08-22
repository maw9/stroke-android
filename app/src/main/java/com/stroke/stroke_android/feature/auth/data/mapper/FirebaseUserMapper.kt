package com.stroke.stroke_android.feature.auth.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.stroke.stroke_android.feature.auth.domain.model.AuthUser
import com.stroke.stroke_android.feature.profile.ui.model.formatPhotoUrl

fun FirebaseUser.asDomain(): AuthUser {
    return AuthUser(
        id = uid,
        name = displayName,
        email = email,
        phone = phoneNumber,
        profileUrl = photoUrl?.path?.formatPhotoUrl()
    )
}