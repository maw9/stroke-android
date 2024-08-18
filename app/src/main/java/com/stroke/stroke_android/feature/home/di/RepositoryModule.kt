package com.stroke.stroke_android.feature.home.di

import com.stroke.stroke_android.feature.home.data.repository.HomePostsRepository
import com.stroke.stroke_android.feature.home.data.repository.HomePostsRepositoryImpl
import org.koin.dsl.module

val homePostsRepositoryModule = module {
    single {
        HomePostsRepositoryImpl(get()) as HomePostsRepository
    }
}