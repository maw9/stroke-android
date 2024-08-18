package com.stroke.stroke_android.feature.postdetails.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.FragmentPostDetailsBinding
import com.stroke.stroke_android.feature.postdetails.ui.viewmodel.PostDetailsUIState
import com.stroke.stroke_android.feature.postdetails.ui.viewmodel.PostDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailsFragment(private val id: String) : Fragment() {

    private var _binding: FragmentPostDetailsBinding? = null
    private val binding: FragmentPostDetailsBinding get() = _binding!!

    private val viewModel: PostDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPostDetailsBinding.inflate(inflater).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.getPostDetails(id)

        viewModel.detailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                PostDetailsUIState.Loading -> {
                    binding.pbDetailsLoading.visibility = View.VISIBLE
                }

                is PostDetailsUIState.Success -> {
                    binding.pbDetailsLoading.visibility = View.GONE

                    Glide.with(requireContext())
                        .load(it.data?.imageUrl)
                        .into(binding.ivPostImage)

                    (binding.btnFavorite as MaterialButton).icon = AppCompatResources.getDrawable(
                        binding.root.context,
                        if (it.data?.isFavorite == true) {
                            R.drawable.ic_favorite
                        } else {
                            R.drawable.ic_favorite_outlined
                        }
                    )

                    binding.tvDescription.text = it.data?.description.orEmpty()

                    binding.tvCreatedAt.text = it.data?.createdAt.orEmpty()

                    Glide.with(requireContext())
                        .load(it.data?.owner?.profileImageUrl)
                        .into(binding.ivUserProfile)

                    binding.tvUsername.text = it.data?.owner?.name.orEmpty()

                    it.data?.tags?.map {
                        Chip(requireContext()).also { chip ->
                            chip.text = it
                        }
                    }?.forEach { chip ->
                        binding.chipGpTags.addView(chip)
                    }
                }

                is PostDetailsUIState.Error -> {
                    binding.pbDetailsLoading.visibility = View.GONE
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getInstance(id: String) = PostDetailsFragment(id)
    }

}