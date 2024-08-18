package com.stroke.stroke_android.feature.postdetails.data.repository

import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail

interface PostDetailsRepository {

    suspend fun getPostDetails(id: String): Result<PostDetail?>

}