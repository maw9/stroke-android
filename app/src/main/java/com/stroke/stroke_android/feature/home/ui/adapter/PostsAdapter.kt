package com.stroke.stroke_android.feature.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.stroke.stroke_android.databinding.ItemPostBinding
import com.stroke.stroke_android.feature.home.ui.model.Post
import com.stroke.stroke_android.feature.home.ui.viewholder.ItemPostVH

class PostsAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<Post, ItemPostVH>(PostsDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPostVH {
        return ItemPostVH(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ItemPostVH, position: Int) {
        holder.bind(getItem(position))
    }
}

object PostsDiffUtil : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}