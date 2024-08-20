package com.stroke.stroke_android.feature.profile.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface ProfileRepository {

    suspend fun getProfileByUid(): Result<Task<DocumentSnapshot>>

}