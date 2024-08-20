package com.stroke.stroke_android.feature.profile.ui.model

data class UserProfile(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val photoUrl: String?
)