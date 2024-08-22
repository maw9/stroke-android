package com.stroke.stroke_android.feature.profile.data.datasource

import android.content.Context
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.stroke.stroke_android.feature.profile.ui.model.Gender
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile
import com.stroke.stroke_android.feature.profile.ui.model.asProfileRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.InputStream

class FirebaseProfileDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val appContext: Context
) : ProfileDataSource {

    override suspend fun getProfileByUid(uid: String): Result<UserProfile?> {
        return try {
            val dataSnapshot = firestore.collection("profiles")
                .document(uid)
                .get()
                .await()
            if (dataSnapshot.data != null) {
                Result.success(
                    UserProfile(
                        id = dataSnapshot.data?.get("id").toString(),
                        name = dataSnapshot.data?.get("name").toString(),
                        phone = dataSnapshot.data?.get("phone").toString(),
                        email = dataSnapshot.data?.get("email").toString(),
                        photoUrl = dataSnapshot.data?.get("photoUrl").toString(),
                        gender = parseGender((dataSnapshot.data?.get("gender") as Long).toInt()),
                        description = dataSnapshot.data?.get("description").toString()
                    )
                )
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(Exception(e))
        }
    }

    private fun parseGender(input: Int): Gender {
        return when (input) {
            0 -> Gender.Male
            1 -> Gender.Female
            2 -> Gender.Other
            else -> Gender.Male
        }
    }

    override suspend fun saveProfile(profile: UserProfile): Result<String> {
        return try {
            firestore.collection("profiles")
                .document(profile.id)
                .set(profile.asProfileRequest())
                .await()
            Result.success("User profile has been successfully saved!")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun uploadProfilePhoto(uri: Uri): Result<Task<Uri>> {
        var inputStream: InputStream? = null
        return try {
            inputStream = appContext.contentResolver.openInputStream(uri)
            val ref = firebaseStorage.reference.child("images/${uri.lastPathSegment}")
            if (inputStream != null) {
                ref.putStream(inputStream).await()
            }
            Result.success(ref.downloadUrl)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            withContext(Dispatchers.IO) { inputStream?.close() }
        }
    }

}