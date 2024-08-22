package com.stroke.stroke_android.feature.profile.data.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.stroke.stroke_android.feature.profile.ui.model.Gender
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile
import com.stroke.stroke_android.feature.profile.ui.model.asProfileRequest
import kotlinx.coroutines.tasks.await

class FirebaseProfileDataSourceImpl(private val firestore: FirebaseFirestore) : ProfileDataSource {

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
            Log.i("Profile", e.message ?: "Empty")
            Result.failure(Exception("Something went wrong!"))
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
            Result.failure(Exception("Something went wrong!"))
        }
    }

}