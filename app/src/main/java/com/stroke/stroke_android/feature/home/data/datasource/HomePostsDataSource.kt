package com.stroke.stroke_android.feature.home.data.datasource

import com.stroke.stroke_android.feature.home.ui.model.Post

interface HomePostsDataSource {

    suspend fun getPosts(): Result<List<Post>>

}