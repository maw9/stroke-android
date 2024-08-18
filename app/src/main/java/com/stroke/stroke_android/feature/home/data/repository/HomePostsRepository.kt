package com.stroke.stroke_android.feature.home.data.repository

import com.stroke.stroke_android.feature.home.ui.model.Post

interface HomePostsRepository {

    suspend fun getPosts(): Result<List<Post>>

}