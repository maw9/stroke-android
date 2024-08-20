package com.stroke.stroke_android

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stroke.stroke_android.feature.auth.data.repository.AuthRepository
import com.stroke.stroke_android.feature.onboarding.ui.OnboardingActivity
import com.stroke.stroke_android.util.exception.UserNotLoginException
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val authRepository: AuthRepository by inject()
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
            return
        }

        lifecycleScope.launch {
            authRepository.getCurrentUser().fold(
                {
                    navigateHome()
                },
                {
                    if (it is UserNotLoginException) {
                        launchFirebaseAuthActivity()
                    }
                }
            )
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
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse

        if (result.resultCode == RESULT_OK) {
            val uid = FirebaseAuth.getInstance().currentUser?.uid.orEmpty()
            Firebase.firestore
                .collection("profiles")
                .document(uid)
                .get().addOnSuccessListener { document ->
                    sharedPreferences.edit {
                        putBoolean(getString(R.string.is_profile_data_filled), document.data != null)
                    }
                    navigateHome()
                }.addOnFailureListener {
                    navigateHome()
                }
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