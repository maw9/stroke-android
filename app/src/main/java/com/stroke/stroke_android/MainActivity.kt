package com.stroke.stroke_android

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.stroke.HomeActivity
import com.stroke.stroke_android.feature.onboarding.ui.OnboardingActivity
import org.koin.android.ext.android.inject

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

        if (FirebaseAuth.getInstance().currentUser == null) {
            launchFirebaseAuthActivity()
        } else {
            navigateHome()
        }

    }

    private fun launchFirebaseAuthActivity() {
        val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract(),
        ) { res ->
            this.onSignInResult(res)
        }

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.login_logo)
            .setTheme(R.style.AppTheme)
            .build()
//            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse

        if (result.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            navigateHome()
        } else {
            if (response == null) {
                finish()
            } else {
                Toast.makeText(
                    this,
                    response.error?.message ?: "Something went wrong!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun navigateHome() {
        startActivity(
            Intent(this, HomeActivity::class.java).also {
                it.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                )
            }
        )
    }

}