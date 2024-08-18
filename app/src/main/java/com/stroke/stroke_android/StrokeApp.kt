package com.stroke.stroke_android

import android.app.Application
import com.stroke.stroke_android.common.di.localStorageModule
import com.stroke.stroke_android.feature.home.di.homePostsDataSourceModule
import com.stroke.stroke_android.feature.home.di.homePostsRepositoryModule
import com.stroke.stroke_android.feature.home.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StrokeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StrokeApp)
            modules(
                localStorageModule,
                homeViewModelModule,
                homePostsRepositoryModule,
                homePostsDataSourceModule
            )
        }

    }

}