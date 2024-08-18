package com.stroke.stroke_android.feature.postdetails.di

import com.stroke.stroke_android.feature.postdetails.data.datasource.PostDetailsDataSource
import com.stroke.stroke_android.feature.postdetails.data.datasource.PostDetailsDataSourceImpl
import org.koin.dsl.module

val postDetailsDataSourceModule = module {
    single {
        PostDetailsDataSourceImpl(get()) as PostDetailsDataSource
    }
}