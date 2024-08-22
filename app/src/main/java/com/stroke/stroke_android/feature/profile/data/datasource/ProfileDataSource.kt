package com.stroke.stroke_android.feature.profile.data.datasource

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

interface ProfileDataSource {

    suspend fun getProfileByUid(uid: String): Result<UserProfile?>

    suspend fun saveProfile(profile: UserProfile): Result<String>

    suspend fun uploadProfilePhoto(uri: Uri): Result<Task<Uri>>

}