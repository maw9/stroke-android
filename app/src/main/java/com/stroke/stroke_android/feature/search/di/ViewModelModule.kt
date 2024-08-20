package com.stroke.stroke_android.feature.search.di

import com.stroke.stroke_android.feature.search.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchViewModelModule = module {
    viewModel {
        SearchViewModel(get())
    }
}