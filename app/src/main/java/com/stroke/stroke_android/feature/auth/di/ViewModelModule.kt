package com.stroke.stroke_android.feature.auth.di

import com.stroke.stroke_android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}