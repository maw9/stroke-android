package com.stroke.stroke_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.stroke.stroke_android.databinding.ActivityHomeBinding
import com.stroke.stroke_android.feature.profile.data.repository.ProfileRepository
import com.stroke.stroke_android.feature.profile.ui.screen.UpdateProfileFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding.btnLogout.setOnClickListener {
//            AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener {
//                    startActivity(
//                        Intent(this, MainActivity::class.java).also {
//                            it.addFlags(
//                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                            )
//                        }
//                    )
//                }
//        }

    }

}