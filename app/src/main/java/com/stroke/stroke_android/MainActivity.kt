package com.stroke.stroke_android

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.stroke.stroke_android.feature.onboarding.ui.OnboardingActivity
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isStartOnboarding = sharedPreferences.getBoolean(
            getString(R.string.is_onboarding_shown_key),
            false
        )
        if (!isStartOnboarding) {
            startActivity(Intent(this, OnboardingActivity::class.java).also {
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }

    }
}