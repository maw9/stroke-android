package com.stroke.stroke_android.feature.auth.di

import com.stroke.stroke_android.feature.auth.data.repository.AuthRepository
import com.stroke.stroke_android.feature.auth.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val authRepositoryModule = module {
    single {
        AuthRepositoryImpl(get()) as AuthRepository
    }
}