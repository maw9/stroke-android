package com.stroke.stroke_android.feature.home.ui.model

data class Post(
    val id: String,
    val description: String,
    val createdAt: String,
    val imageUrl: String,
    val owner: User?,
    var isFavorite: Boolean
)
