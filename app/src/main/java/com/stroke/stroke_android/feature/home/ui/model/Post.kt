package com.stroke.stroke_android.feature.home.ui.model

import android.graphics.Bitmap

data class Post(
    val id: String,
    val description: String,
    val createdAt: String,
    val blurHash: Bitmap?,
    val imageUrl: String,
    val owner: User?,
    var isFavorite: Boolean
)
