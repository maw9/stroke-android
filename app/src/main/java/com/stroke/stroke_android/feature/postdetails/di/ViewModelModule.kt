package com.stroke.stroke_android.feature.postdetails.di

import com.stroke.stroke_android.feature.postdetails.ui.viewmodel.PostDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postDetailsViewModelModule = module {
    viewModel {
        PostDetailsViewModel(get())
    }
}