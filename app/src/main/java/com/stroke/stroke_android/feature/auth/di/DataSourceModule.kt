package com.stroke.stroke_android.feature.auth.di

import com.stroke.stroke_android.feature.auth.data.datasource.AuthDataSource
import com.stroke.stroke_android.feature.auth.data.datasource.FirebaseAuthDataSourceImpl
import org.koin.dsl.module

val authDataSourceModule = module {
    single {
        FirebaseAuthDataSourceImpl(get()) as AuthDataSource
    }
}