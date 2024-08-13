package com.stroke.stroke_android

import android.app.Application
import org.koin.core.context.startKoin

class StrokeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules()
        }

    }

}