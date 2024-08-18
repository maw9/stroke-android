package com.stroke.stroke_android.feature.postdetails.di

import com.stroke.stroke_android.feature.postdetails.data.repository.PostDetailsRepository
import com.stroke.stroke_android.feature.postdetails.data.repository.PostDetailsRepositoryImpl
import org.koin.dsl.module

val postDetailsRepositoryModule = module {
    single {
        PostDetailsRepositoryImpl(get()) as PostDetailsRepository
    }
}