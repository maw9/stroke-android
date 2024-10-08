package com.stroke.stroke_android.feature.profile.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.stroke.stroke_android.feature.auth.data.datasource.AuthDataSource
import com.stroke.stroke_android.feature.profile.data.datasource.ProfileDataSource
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

class ProfileRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun getProfileByUid(): Result<UserProfile?> {
        return authDataSource.getCurrentUser().fold(
            {
                profileDataSource.getProfileByUid(it.id)
            },
            {
                Result.failure(Exception("No uid to fetch profile."))
            }
        )
    }

    override suspend fun saveProfile(profile: UserProfile): Result<String> {
        return profileDataSource.saveProfile(profile)
    }

    override suspend fun uploadProfilePhoto(uri: Uri): Result<Task<Uri>> {
        return profileDataSource.uploadProfilePhoto(uri)
    }

}