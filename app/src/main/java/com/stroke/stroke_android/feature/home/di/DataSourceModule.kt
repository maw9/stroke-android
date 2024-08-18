package com.stroke.stroke_android.feature.home.di

import com.stroke.stroke_android.feature.home.data.datasource.HomePostsDataSource
import com.stroke.stroke_android.feature.home.data.datasource.MockHomePostsDataSourceImpl
import org.koin.dsl.module

val homePostsDataSourceModule = module {
    single {
        MockHomePostsDataSourceImpl() as HomePostsDataSource
    }
}