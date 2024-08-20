package com.stroke.stroke_android.feature.auth.data.datasource

import com.stroke.stroke_android.feature.auth.domain.model.AuthUser

class MockAuthDataSourceImpl : AuthDataSource {

    override suspend fun getCurrentUser(): Result<AuthUser> {
        return Result.success(AuthUser("", null, null, null, null))
    }

}