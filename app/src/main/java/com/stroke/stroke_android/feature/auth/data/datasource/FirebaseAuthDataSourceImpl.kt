package com.stroke.stroke_android.feature.auth.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.stroke.stroke_android.feature.auth.data.mapper.asDomain
import com.stroke.stroke_android.feature.auth.domain.model.AuthUser
import com.stroke.stroke_android.util.exception.NewUserException

class FirebaseAuthDataSourceImpl(private val auth: FirebaseAuth) : AuthDataSource {

    override suspend fun getCurrentUser(): Result<AuthUser> {
        return if (auth.currentUser != null) {
            Result.success(auth.currentUser!!.asDomain())
        } else {
            Result.failure(NewUserException("User info is not filled!"))
        }
    }

}