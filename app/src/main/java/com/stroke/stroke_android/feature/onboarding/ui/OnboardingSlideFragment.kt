package com.stroke.stroke_android.feature.onboarding.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.FragmentOnboardingSlideBinding
import com.stroke.stroke_android.feature.onboarding.domain.OnboardingSlideData

class OnboardingSlideFragment(private val data: OnboardingSlideData) : Fragment() {

    private var _binding: FragmentOnboardingSlideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingSlideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSlideTitle.text = data.title
        binding.animationView.setAnimation(data.rawAnimationRes)
        binding.tvSlideDescription.text = data.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getInstance(data: OnboardingSlideData) = OnboardingSlideFragment(data)
    }

}