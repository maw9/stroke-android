package com.stroke.stroke_android.feature.postdetails.data.mapper

import com.stroke.stroke_android.feature.home.ui.model.User
import com.stroke.stroke_android.feature.postdetails.data.model.response.PostDetailsResponse
import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail

fun PostDetailsResponse.asDomain(): PostDetail {
    return PostDetail(
        id = id.orEmpty(),
        description = description.orEmpty(),
        createdAt = createdAt.orEmpty(),
        imageUrl = urls?.raw.orEmpty(),
        owner = user?.let {
            User(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                profileImageUrl = it.profileImage?.medium.orEmpty()
            )
        },
        isFavorite = false,
        tags = tags?.map { it.title.orEmpty() }.orEmpty()
    )
}