package com.stroke.stroke_android.feature.search.ui.screen

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.FragmentSearchBinding
import com.stroke.stroke_android.feature.home.ui.adapter.PostsAdapter
import com.stroke.stroke_android.feature.postdetails.ui.screen.PostDetailsFragment
import com.stroke.stroke_android.feature.search.ui.viewmodel.SearchUIModel
import com.stroke.stroke_android.feature.search.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tieSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(binding.tieSearch.text.toString())
                hideKeyboardFrom(requireContext(), binding.tieSearch)
            }
            true
        }

        val adapter = PostsAdapter {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainerView,
                    PostDetailsFragment.getInstance(it),
                    "post_details"
                )
                .addToBackStack(null)
                .commit()
        }
        binding.rvSearchResults.adapter = adapter

        viewModel.searchResultLiveData.observe(viewLifecycleOwner) {
            when (it) {
                SearchUIModel.Loading -> {
                    adapter.submitList(emptyList())
                    binding.tvNoResult.visibility = View.INVISIBLE
                    binding.pbLoading.visibility = View.VISIBLE
                }

                is SearchUIModel.Success -> {
                    binding.pbLoading.visibility = View.INVISIBLE
                    adapter.submitList(it.data)
                }

                SearchUIModel.EmptyResult -> {
                    binding.pbLoading.visibility = View.INVISIBLE
                    binding.tvNoResult.visibility = View.VISIBLE
                }

                is SearchUIModel.Error -> {
                    binding.pbLoading.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getInstance() = SearchFragment()
    }

}