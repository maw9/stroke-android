package com.stroke.stroke_android.feature.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stroke.stroke_android.R
import com.stroke.stroke_android.feature.onboarding.domain.OnboardingSlideData
import com.stroke.stroke_android.feature.onboarding.ui.OnboardingSlideFragment

class OnboardingSlideAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    private val slideDataList = listOf(
        OnboardingSlideData(
            "Seamless Access",
            R.raw.auth_animation,
            "Easily log in to access your personalized art gallery experience. Save your progress, view your favorites, and pick up where you left off across all your devices."
        ),
        OnboardingSlideData(
            "Find Your Inspiration",
            R.raw.slide_2,
            "Quickly search through our vast collection of artworks with advanced filters. Whether you're looking for a specific piece or just browsing, finding the perfect artwork has never been easier."
        ),
        OnboardingSlideData(
            "Curate Your Favorites",
            R.raw.slide_3,
            "Add artworks to your favorites with a single tap. Create your personal collection and revisit your top picks whenever inspiration strikes."
        ),
    )

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return OnboardingSlideFragment.getInstance(slideDataList[position])
    }
}