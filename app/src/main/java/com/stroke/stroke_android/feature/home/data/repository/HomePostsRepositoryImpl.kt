package com.stroke.stroke_android.feature.home.data.repository

import com.stroke.stroke_android.feature.home.data.datasource.HomePostsDataSource
import com.stroke.stroke_android.feature.home.ui.model.Post

class HomePostsRepositoryImpl(private val dataSource: HomePostsDataSource) : HomePostsRepository {

    override suspend fun getPosts(): Result<List<Post>> {
        return dataSource.getPosts()
    }

}