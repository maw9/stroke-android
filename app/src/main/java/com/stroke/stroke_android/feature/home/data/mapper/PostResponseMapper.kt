package com.stroke.stroke_android.feature.home.data.mapper

import com.stroke.stroke_android.feature.home.data.model.response.PostResponse
import com.stroke.stroke_android.feature.home.ui.model.Post
import com.stroke.stroke_android.feature.home.ui.model.User

fun PostResponse.asDomain(): Post {
    return Post(
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
        isFavorite = false
    )
}