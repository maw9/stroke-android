package com.stroke.stroke_android.feature.profile.di

import com.stroke.stroke_android.feature.profile.data.datasource.FirebaseProfileDataSourceImpl
import com.stroke.stroke_android.feature.profile.data.datasource.ProfileDataSource
import org.koin.dsl.module

val profileDataSource = module {
    single {
        FirebaseProfileDataSourceImpl(get()) as ProfileDataSource
    }
}