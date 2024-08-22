package com.stroke.stroke_android.feature.profile.data.repository

import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

interface ProfileRepository {

    suspend fun getProfileByUid(): Result<UserProfile?>

    suspend fun saveProfile(profile: UserProfile): Result<String>

}