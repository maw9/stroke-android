package com.stroke.stroke_android.feature.postdetails.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.stroke.stroke_android.databinding.FragmentPhotoViewBinding

class PhotoViewFragment : Fragment() {

    private var _binding: FragmentPhotoViewBinding? = null
    private val binding: FragmentPhotoViewBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString("url")

        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.photoView)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getInstance(imageUrl: String): PhotoViewFragment {
            val args = Bundle()
            args.putString("url", imageUrl)
            return PhotoViewFragment().also {
                it.arguments = args
            }
        }
    }

}