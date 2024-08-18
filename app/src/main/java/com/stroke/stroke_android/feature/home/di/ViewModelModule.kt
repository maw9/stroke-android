package com.stroke.stroke_android.feature.home.di

import com.stroke.stroke_android.feature.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}