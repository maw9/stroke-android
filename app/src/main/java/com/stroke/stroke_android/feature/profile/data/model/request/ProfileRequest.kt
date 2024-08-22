package com.stroke.stroke_android.feature.profile.data.model.request

import com.stroke.stroke_android.feature.profile.ui.model.Gender

data class ProfileRequest(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val photoUrl: String?,
    val gender: Int,
    val description: String
)
