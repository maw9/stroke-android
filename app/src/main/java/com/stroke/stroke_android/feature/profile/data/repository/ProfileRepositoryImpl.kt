package com.stroke.stroke_android.feature.profile.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.stroke.stroke_android.feature.auth.data.datasource.AuthDataSource
import com.stroke.stroke_android.feature.profile.data.datasource.ProfileDataSource

class ProfileRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun getProfileByUid(): Result<Task<DocumentSnapshot>> {
        return authDataSource.getCurrentUser().fold(
            {
                Result.success(profileDataSource.getProfileByUid(it.id))
            },
            {
                Result.failure(Exception("No uid to fetch profile."))
            }
        )
    }

}