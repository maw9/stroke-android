package com.stroke.stroke_android.common.di

import android.content.Context
import com.stroke.stroke_android.R
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localStorageModule = module {
    single {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
    }
}