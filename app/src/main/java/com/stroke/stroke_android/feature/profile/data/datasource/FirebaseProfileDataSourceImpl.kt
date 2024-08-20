package com.stroke.stroke_android.feature.profile.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseProfileDataSourceImpl(private val firestore: FirebaseFirestore) : ProfileDataSource {

    override suspend fun getProfileByUid(uid: String): Task<DocumentSnapshot> {
        return firestore.collection("profiles").document(uid).get()
        /*.addOnSuccessListener { document ->
            if (document != null) {
                return@addOnSuccessListener Result.success(
                    UserProfile(
                        id = document.data?.get("id").toString(),
                        name = document.data?.get("name").toString(),
                        phone = document.data?.get("phone").toString(),
                        email = document.data?.get("email").toString(),
                        photoUrl = document.data?.get("photo_url").toString(),
                    )
                )
            } else {
                return@addOnSuccessListener Result.failure(NoProfileException("No profile found!"))
            }
        }
        .addOnFailureListener { exception ->
            return@addOnSuccessListener Result.failure<Throwable>(exception)
        }*/
    }

}