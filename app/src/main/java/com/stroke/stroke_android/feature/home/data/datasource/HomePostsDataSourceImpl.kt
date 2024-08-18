package com.stroke.stroke_android.feature.home.data.datasource

import com.stroke.stroke_android.common.utils.handle
import com.stroke.stroke_android.feature.home.data.mapper.asDomain
import com.stroke.stroke_android.feature.home.data.model.response.PostResponse
import com.stroke.stroke_android.feature.home.ui.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.request.get

const val ACCESS_KEY = "_NyZWtmWZMIAg1HyzxBkO-E71hd5VOi842YsUmapIXk"

class HomePostsDataSourceImpl(private val httpClient: HttpClient) : HomePostsDataSource {

    override suspend fun getPosts(): Result<List<Post>> {
        return handle<List<PostResponse>> {
            httpClient.get("photos") {
                url {
                    parameters.append("client_id", ACCESS_KEY)
                }
            }
        }.map { networkPosts ->
            networkPosts.map { it.asDomain() }
        }
    }

}
