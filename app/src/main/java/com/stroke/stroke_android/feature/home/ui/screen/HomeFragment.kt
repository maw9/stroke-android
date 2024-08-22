package com.stroke.stroke_android.feature.home.ui.screen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.FragmentHomeBinding
import com.stroke.stroke_android.feature.home.ui.adapter.PostsAdapter
import com.stroke.stroke_android.feature.home.ui.viewmodel.HomePostsUIState
import com.stroke.stroke_android.feature.home.ui.viewmodel.HomeViewModel
import com.stroke.stroke_android.feature.postdetails.ui.screen.PostDetailsFragment
import com.stroke.stroke_android.feature.profile.ui.screen.ProfileFragment
import com.stroke.stroke_android.feature.profile.ui.screen.UpdateProfileFragment
import com.stroke.stroke_android.feature.search.ui.screen.SearchFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sharedPreferences: SharedPreferences by inject()

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences.getBoolean(getString(R.string.is_profile_data_filled), false).let {
            if (!it) {
                goToProfileUpdateScreen()
            }
        }

        binding.iBtnSearch.setOnClickListener { goToSearch() }

        Glide.with(this)
            .load("https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg")
            .into(binding.sivProfile)

        val adapter = PostsAdapter { id ->
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainerView,
                    PostDetailsFragment.getInstance(id),
                    "post_details"
                )
                .addToBackStack(null)
                .commit()
        }
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

        binding.sivProfile.setOnClickListener { goToProfile() }
    }

    private fun goToProfile() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                ProfileFragment.getInstance(),
                "profile"
            )
            .addToBackStack(null)
            .commit()
    }

    private fun goToProfileUpdateScreen() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                UpdateProfileFragment.getInstance(),
                "update_profile"
            )
            .addToBackStack(null)
            .commit()
    }

    private fun goToSearch() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                SearchFragment.getInstance(),
                "search"
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}