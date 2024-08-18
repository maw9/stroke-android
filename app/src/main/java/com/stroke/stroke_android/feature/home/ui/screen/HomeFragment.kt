package com.stroke.stroke_android.feature.home.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.stroke.stroke_android.databinding.FragmentHomeBinding
import com.stroke.stroke_android.feature.home.ui.adapter.PostsAdapter
import com.stroke.stroke_android.feature.home.ui.viewmodel.HomePostsUIState
import com.stroke.stroke_android.feature.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load("https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg")
            .into(binding.sivProfile)

        val adapter = PostsAdapter()
        binding.rvPosts.adapter = adapter

        viewModel.postsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                HomePostsUIState.Loading -> {
                    binding.pbPostsLoading.visibility = View.VISIBLE
                }

                is HomePostsUIState.Success -> {
                    binding.pbPostsLoading.visibility = View.GONE
                    adapter.submitList(it.posts)
                }

                is HomePostsUIState.Error -> {
                    binding.pbPostsLoading.visibility = View.GONE
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}