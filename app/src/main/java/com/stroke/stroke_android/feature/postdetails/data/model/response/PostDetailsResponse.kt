package com.stroke.stroke_android.feature.postdetails.data.model.response

import com.stroke.stroke_android.feature.home.data.model.response.URLsResponse
import com.stroke.stroke_android.feature.home.data.model.response.UserResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDetailsResponse(
    val id: String?,

    @SerialName("created_at")
    val createdAt: String?,

    @SerialName("blur_hash")
    val blurHash: String?,

    val width: Int?,

    val height: Int?,

    @SerialName("alt_description")
    val description: String?,

    val urls: URLsResponse?,

    val user: UserResponse?,

    val tags: List<TagResponse>?

)

@Serializable
data class TagResponse(
    val type: String?,
    val title: String?
)
