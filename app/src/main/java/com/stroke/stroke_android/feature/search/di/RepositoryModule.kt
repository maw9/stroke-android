package com.stroke.stroke_android.feature.search.di

import com.stroke.stroke_android.feature.search.data.repository.SearchRepository
import com.stroke.stroke_android.feature.search.data.repository.SearchRepositoryImpl
import org.koin.dsl.module

val searchRepositoryModule = module {
    single {
        SearchRepositoryImpl(get()) as SearchRepository
    }
}