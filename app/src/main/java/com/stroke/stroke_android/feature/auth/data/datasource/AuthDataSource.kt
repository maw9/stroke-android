package com.stroke.stroke_android.feature.auth.data.datasource

import com.stroke.stroke_android.feature.auth.domain.model.AuthUser

interface AuthDataSource {

    suspend fun getCurrentUser(): Result<AuthUser>

}