package com.stroke.stroke_android.feature.search.data.repository

import com.stroke.stroke_android.feature.home.ui.model.Post
import com.stroke.stroke_android.feature.search.data.datasource.SearchDataSource

class SearchRepositoryImpl(private val dataSource: SearchDataSource) : SearchRepository {

    override suspend fun searchPosts(keyword: String): Result<List<Post>> {
        return dataSource.searchPosts(keyword)
    }

}