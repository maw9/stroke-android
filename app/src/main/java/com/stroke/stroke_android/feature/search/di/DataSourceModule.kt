package com.stroke.stroke_android.feature.search.di

import com.stroke.stroke_android.feature.search.data.datasource.SearchDataSource
import com.stroke.stroke_android.feature.search.data.datasource.SearchDataSourceImpl
import org.koin.dsl.module

val searchDataSourceModule = module {
    single {
        SearchDataSourceImpl(get()) as SearchDataSource
    }
}