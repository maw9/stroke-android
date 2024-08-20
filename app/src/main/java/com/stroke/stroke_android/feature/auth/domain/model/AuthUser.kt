package com.stroke.stroke_android.feature.auth.domain.model

data class AuthUser(
    val id: String,
    val name: String?,
    val email: String?,
    val phone: String?,
    val profileUrl: String?
)
