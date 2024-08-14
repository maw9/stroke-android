package com.stroke.stroke_android.feature.onboarding.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.stroke.stroke_android.MainActivity
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.ActivityOnboardingBinding
import com.stroke.stroke_android.feature.onboarding.adapter.OnboardingSlideAdapter
import org.koin.android.ext.android.inject

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ActivityOnboardingBinding.inflate(layoutInflater).also {
            binding = it
            setContentView(it.root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = OnboardingSlideAdapter(this)
        binding.pager.adapter = adapter

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateActionButton(position)
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.pager.currentItem < 2) {
                binding.pager.currentItem += 1
            } else {
                sharedPreferences.edit {
                    putBoolean(getString(R.string.is_onboarding_shown_key), true)
                }
                navigate()
            }
        }

        this.onBackPressedDispatcher.addCallback(this) {
            if (binding.pager.currentItem == 0) {
                finish()
            } else {
                binding.pager.currentItem -= 1
            }
        }
    }

    private fun updateActionButton(position: Int) {
        binding.btnNext.text = if (position == 2) {
            "Get Started"
        } else {
            "Next"
        }
    }

    private fun navigate() {
        startActivity(
            Intent(this, MainActivity::class.java).also {
                it.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                )
            }
        )
    }

}