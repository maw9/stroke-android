package com.stroke.stroke_android.feature.home.data.datasource

import com.stroke.stroke_android.feature.home.ui.model.Post

class HomePostsDataSourceImpl : HomePostsDataSource {

    override suspend fun getPosts(): Result<List<Post>> {
        return Result.success(emptyList())
    }

}