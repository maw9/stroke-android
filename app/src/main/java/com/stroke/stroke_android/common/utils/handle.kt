package com.stroke.stroke_android.common.utils

import com.stroke.stroke_android.common.exception.ApiException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> handle(
    apiCall: () -> HttpResponse
): Result<T> {
    return try {
        val httpResponse = apiCall()
        when (httpResponse.status) {
            //200
            HttpStatusCode.OK -> {
                //Success?
                val response: T = httpResponse.body()
                if (response != null) {
                    return Result.success(response)
                }
                //Fail or Success?
                return Result.failure(
                    ApiException(
                        code = httpResponse.status.value,
                        message = "Something went wrong"
                    )
                )
            }

            //404
            HttpStatusCode.NotFound -> Result.failure(
                ApiException(
                    code = httpResponse.status.value,
                    message = "NEW_USER"
                )
            )

            else -> Result.failure(
                ApiException(
                    code = httpResponse.status.value,
                    message = "Something went wrong"
                )
            )
        }
    } catch (e: Exception) {
        //Fail or Success?
        return Result.failure(e)
    }
}