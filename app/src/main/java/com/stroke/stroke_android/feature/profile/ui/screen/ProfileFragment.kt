package com.stroke.stroke_android.feature.profile.ui.screen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.stroke.stroke_android.MainActivity
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.FragmentProfileBinding
import com.stroke.stroke_android.feature.profile.ui.viewmodel.ProfileUIState
import com.stroke.stroke_android.feature.profile.ui.viewmodel.ProfileViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!
    private val sharedPreferences: SharedPreferences by inject()

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile()

        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            when (it) {
                ProfileUIState.Loading -> {
                    showLoading()
                }

                is ProfileUIState.Success -> {
                    hideLoading()
                    if (!it.data.photoUrl.isNullOrEmpty())
                        Glide.with(requireContext()).load(it.data.photoUrl)
                            .into(binding.ivUserPhoto)
                    binding.tvName.text = it.data.name
                    binding.tvDescription.text = it.data.description
                    binding.tvPhone.text = it.data.phone
                    binding.tvEmail.text = it.data.email
                    binding.tvGender.text = it.data.gender.name
                }

                is ProfileUIState.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            AuthUI.getInstance()
                .signOut(requireContext())
                .addOnCompleteListener {
                    sharedPreferences.edit {
                        putBoolean("is_profile_data_filled", false)
                    }
                    startActivity(
                        Intent(requireContext(), MainActivity::class.java).also {
                            it.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            )
                        }
                    )
                }
        }

        binding.btnEdit.setOnClickListener { goToUpdateProfile() }

        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    private fun goToUpdateProfile() {
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

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
        binding.vLoadingBg.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.INVISIBLE
        binding.vLoadingBg.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getInstance() = ProfileFragment()
    }

}