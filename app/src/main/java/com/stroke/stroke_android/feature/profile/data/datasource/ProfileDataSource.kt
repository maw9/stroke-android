package com.stroke.stroke_android.feature.profile.data.datasource

import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

interface ProfileDataSource {

    suspend fun getProfileByUid(uid: String): Result<UserProfile?>

    suspend fun saveProfile(profile: UserProfile): Result<String>

}