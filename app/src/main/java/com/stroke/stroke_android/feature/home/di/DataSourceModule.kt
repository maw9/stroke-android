package com.stroke.stroke_android.feature.home.di

import com.stroke.stroke_android.feature.home.data.datasource.HomePostsDataSource
import com.stroke.stroke_android.feature.home.data.datasource.HomePostsDataSourceImpl
import org.koin.dsl.module

val homePostsDataSourceModule = module {
    single {
        HomePostsDataSourceImpl(get()) as HomePostsDataSource
    }
}