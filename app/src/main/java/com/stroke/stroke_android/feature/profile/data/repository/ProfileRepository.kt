package com.stroke.stroke_android.feature.profile.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

interface ProfileRepository {

    suspend fun getProfileByUid(): Result<UserProfile?>

    suspend fun saveProfile(profile: UserProfile): Result<String>

    suspend fun uploadProfilePhoto(uri: Uri): Result<Task<Uri>>

}