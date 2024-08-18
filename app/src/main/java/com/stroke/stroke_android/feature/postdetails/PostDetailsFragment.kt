package com.stroke.stroke_android.feature.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.stroke.stroke_android.databinding.FragmentPostDetailsBinding

class PostDetailsFragment : Fragment() {

    private var _binding: FragmentPostDetailsBinding? = null
    private val binding: FragmentPostDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPostDetailsBinding.inflate(inflater).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load("https://images.unsplash.com/photo-1721332150382-d4114ee27eff?ixid=M3w2MTQ5NTV8MHwxfGFsbHx8fHx8fHx8fDE3MjM5ODkyNzl8&ixlib=rb-4.0.3")
            .into(binding.ivPostImage)

        val mockTags = listOf(
            "tag1",
            "tag2",
            "tag3",
            "tag1",
            "tag2",
            "tag3",
            "tag1",
            "tag2",
            "tag3",
            "tag1",
            "tag2",
            "tag3",
        )
        mockTags.map {
            Chip(requireContext()).also { chip ->
                chip.text = it
            }
        }.forEach {
            binding.chipGpTags.addView(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getInstance() = PostDetailsFragment()
    }

}