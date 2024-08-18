package com.stroke.stroke_android.feature.postdetails.data.datasource

import com.stroke.stroke_android.common.utils.handle
import com.stroke.stroke_android.feature.postdetails.data.mapper.asDomain
import com.stroke.stroke_android.feature.postdetails.data.model.response.PostDetailsResponse
import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail
import io.ktor.client.HttpClient
import io.ktor.client.request.get

const val ACCESS_KEY = "_NyZWtmWZMIAg1HyzxBkO-E71hd5VOi842YsUmapIXk"

class PostDetailsDataSourceImpl(private val httpClient: HttpClient) : PostDetailsDataSource {

    override suspend fun getPostDetails(id: String): Result<PostDetail?> {
        return handle<PostDetailsResponse> {
            httpClient.get("photos/$id") {
                url {
                    parameters.append(
                        "client_id",
                        ACCESS_KEY
                    )
                }
            }
        }.map {
            it.asDomain()
        }
    }

}