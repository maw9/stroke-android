package com.stroke.stroke_android.feature.postdetails.data.repository

import com.stroke.stroke_android.feature.postdetails.data.datasource.PostDetailsDataSource
import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail

class PostDetailsRepositoryImpl(private val dataSource: PostDetailsDataSource) :
    PostDetailsRepository {

    override suspend fun getPostDetails(id: String): Result<PostDetail?> {
        return dataSource.getPostDetails(id)
    }

}