package com.stroke.stroke_android.feature.postdetails.data.mapper

import com.stroke.stroke_android.feature.home.ui.model.User
import com.stroke.stroke_android.feature.postdetails.data.model.response.PostDetailsResponse
import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail
import com.stroke.stroke_android.util.BlurHashDecoder

fun PostDetailsResponse.asDomain(): PostDetail {
    return PostDetail(
        id = id.orEmpty(),
        description = description.orEmpty(),
        createdAt = createdAt.orEmpty(),
        blurredBitmap = blurHash?.let { BlurHashDecoder.decode(it, 20, 12) },
        imageUrl = urls?.getImageUrl().orEmpty(),
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