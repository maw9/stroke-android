package com.stroke.stroke_android.feature.profile.di

import android.app.Application
import android.content.Context
import com.stroke.stroke_android.feature.profile.data.datasource.FirebaseProfileDataSourceImpl
import com.stroke.stroke_android.feature.profile.data.datasource.ProfileDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val profileDataSource = module {
    single {
        FirebaseProfileDataSourceImpl(
            firestore = get(),
            firebaseStorage = get(),
            appContext = androidContext()
        ) as ProfileDataSource
    }
}