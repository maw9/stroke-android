package com.stroke.stroke_android.feature.search.data.datasource

import com.stroke.stroke_android.feature.home.ui.model.Post

interface SearchDataSource {

    suspend fun searchPosts(keyword: String): Result<List<Post>>

}