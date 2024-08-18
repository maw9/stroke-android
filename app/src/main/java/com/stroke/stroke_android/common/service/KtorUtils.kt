package com.stroke.stroke_android.common.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO


class KtorUtils {

    fun createKtor(): HttpClient {
        return HttpClient(CIO) {
//            install(ContentNegotiation) {
//                json(Json {
//                    prettyPrint = true
//                    isLenient = true
//                })
//            }
        }
    }

}