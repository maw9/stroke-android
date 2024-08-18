package com.stroke.stroke_android.feature.home.ui.viewholder

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.ItemPostBinding
import com.stroke.stroke_android.feature.home.ui.model.Post

class ItemPostVH(private val binding: ItemPostBinding, private val onClick: (String) -> Unit) :
    ViewHolder(binding.root) {

    fun bind(data: Post) {
        data.owner?.apply {
            Glide.with(binding.root.context).load(profileImageUrl).into(binding.sivProfile)
            binding.tvOwnerName.text = name
        }

        binding.tvCreatedAt.text = data.createdAt
        Glide.with(binding.root.context).load(data.imageUrl).into(binding.ivPostImage)
        binding.tvPostDescription.text = data.description
        (binding.iBtnFavorite as MaterialButton).icon = AppCompatResources.getDrawable(
            binding.root.context,
            if (data.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_outlined
        )

        binding.root.setOnClickListener { onClick(data.id) }
    }

}