package com.stroke.stroke_android.feature.profile.di

import com.stroke.stroke_android.feature.profile.ui.viewmodel.ProfileViewModel
import com.stroke.stroke_android.feature.profile.ui.viewmodel.UpdateProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileViewModel = module {
    viewModel {
        UpdateProfileViewModel(
            profileRepository = get(),
            authRepository = get(),
            sharedPreferences = get()
        )
    }

    viewModel {
        ProfileViewModel(profileRepository = get())
    }
}