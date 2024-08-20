package com.stroke.stroke_android.feature.search.data.model.response

import com.stroke.stroke_android.feature.home.data.model.response.PostResponse
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultsResponse(
    val results: List<PostResponse>?
)
