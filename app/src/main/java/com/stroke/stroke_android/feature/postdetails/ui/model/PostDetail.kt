package com.stroke.stroke_android.feature.postdetails.ui.model

import com.stroke.stroke_android.feature.home.ui.model.User

data class PostDetail(
    val id: String,
    val description: String,
    val createdAt: String,
    val imageUrl: String,
    val owner: User?,
    var isFavorite: Boolean,
    val tags: List<String>
)
