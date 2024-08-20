package com.stroke.stroke_android.feature.search.data.repository

import com.stroke.stroke_android.feature.home.ui.model.Post

interface SearchRepository {

    suspend fun searchPosts(keyword: String): Result<List<Post>>

}