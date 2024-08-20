package com.stroke.stroke_android.common.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {
    single {
        FirebaseAuth.getInstance()
    }

    single {
        Firebase.firestore
    }
}