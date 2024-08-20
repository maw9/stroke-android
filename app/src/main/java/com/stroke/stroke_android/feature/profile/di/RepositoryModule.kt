package com.stroke.stroke_android.feature.profile.di

import com.stroke.stroke_android.feature.profile.data.repository.ProfileRepository
import com.stroke.stroke_android.feature.profile.data.repository.ProfileRepositoryImpl
import org.koin.dsl.module

val profileRepositoryModule = module {
    single {
        ProfileRepositoryImpl(
            authDataSource = get(),
            profileDataSource = get()
        ) as ProfileRepository
    }
}