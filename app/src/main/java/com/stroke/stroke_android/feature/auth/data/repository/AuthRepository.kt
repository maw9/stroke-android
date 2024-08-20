package com.stroke.stroke_android.feature.auth.data.repository

import com.stroke.stroke_android.feature.auth.domain.model.AuthUser

interface AuthRepository {

    suspend fun getCurrentUser(): Result<AuthUser>

}