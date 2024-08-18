package com.stroke.stroke_android.common.di

import com.stroke.stroke_android.common.service.KtorUtils
import org.koin.dsl.module

val ktorNetworkModule = module {
    single {
        KtorUtils.createKtor()
    }
}