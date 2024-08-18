package com.stroke.stroke_android.feature.postdetails.data.datasource

import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail

interface PostDetailsDataSource {

    suspend fun getPostDetails(id: String): Result<PostDetail?>

}