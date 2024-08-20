package com.stroke.stroke_android.feature.auth.data.repository

import com.stroke.stroke_android.feature.auth.data.datasource.AuthDataSource
import com.stroke.stroke_android.feature.auth.domain.model.AuthUser

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override suspend fun getCurrentUser(): Result<AuthUser> {
        return authDataSource.getCurrentUser()
    }

}