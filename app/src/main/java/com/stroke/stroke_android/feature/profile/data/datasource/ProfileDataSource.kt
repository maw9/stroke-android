package com.stroke.stroke_android.feature.profile.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile

interface ProfileDataSource {

    suspend fun getProfileByUid(uid: String): Task<DocumentSnapshot>

}