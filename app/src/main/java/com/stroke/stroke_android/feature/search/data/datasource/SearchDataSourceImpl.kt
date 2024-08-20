package com.stroke.stroke_android.feature.search.data.datasource

import com.stroke.stroke_android.common.utils.handle
import com.stroke.stroke_android.feature.home.data.datasource.ACCESS_KEY
import com.stroke.stroke_android.feature.home.data.mapper.asDomain
import com.stroke.stroke_android.feature.home.ui.model.Post
import com.stroke.stroke_android.feature.search.data.model.response.SearchResultsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class SearchDataSourceImpl(private val httpClient: HttpClient) : SearchDataSource {

    override suspend fun searchPosts(keyword: String): Result<List<Post>> {
        return handle<SearchResultsResponse> {
            httpClient.get("/search/photos") {
                url {
                    parameters.append("client_id", ACCESS_KEY)
                    parameters.append("query", keyword)
                }
            }
        }.map { resultsResponse ->
            resultsResponse.results?.map { it.asDomain() }.orEmpty()
        }
    }

}