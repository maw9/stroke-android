package com.stroke.stroke_android.feature.profile.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.stroke.stroke_android.R
import com.stroke.stroke_android.databinding.FragmentUpdateProfileBinding
import com.stroke.stroke_android.feature.profile.ui.model.Gender
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile
import com.stroke.stroke_android.feature.profile.ui.viewmodel.UpdateProfileUIState
import com.stroke.stroke_android.feature.profile.ui.viewmodel.UpdateProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileFragment : Fragment() {

    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding: FragmentUpdateProfileBinding get() = _binding!!

    private val viewModel: UpdateProfileViewModel by viewModel()

    private var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.onPhotoPick(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEdit.setOnClickListener {
            pickMedia?.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }

        viewModel.photoLiveData.observe(viewLifecycleOwner) {
            binding.ivProfile.setImageURI(it)
        }

        binding.tieName.addTextChangedListener {
            viewModel.onNameChange(it.toString())
        }

        binding.tiePhone.addTextChangedListener {
            viewModel.onPhoneChange(it.toString())
        }

        binding.tieEmail.addTextChangedListener {
            viewModel.onEmailChange(it.toString())
        }

        binding.radioGpGender.setOnCheckedChangeListener { _, checkedId ->
            viewModel.onGenderChange(checkedId)
        }

        binding.tieDescription.addTextChangedListener {
            viewModel.onDescriptionChange(it.toString())
        }

        viewModel.profileDataLiveData.observe(viewLifecycleOwner) {
            if (viewModel.isProfileFirstTimeFetch) {
                bindExistingProfileData(it)
                viewModel.doneProfileFetching()
            }
            binding.btnSave.isEnabled =
                it.name.isNotBlank() and it.phone.isNotBlank() and it.email.isNotBlank()
        }

        binding.btnSave.setOnClickListener {
            viewModel.submit()
        }

        viewModel.updateProfileLiveData.observe(viewLifecycleOwner) {
            when (it) {
                UpdateProfileUIState.Loading -> {
                    showLoading()
                }

                UpdateProfileUIState.FetchProfileDone -> {
                    hideLoading()
                }

                is UpdateProfileUIState.Success -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }

                is UpdateProfileUIState.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun bindExistingProfileData(data: UserProfile) {
        if (!data.photoUrl.isNullOrEmpty())
            Glide.with(requireContext()).load(data.photoUrl)
                .into(binding.ivProfile)
        binding.tieName.setText(data.name)
        binding.tiePhone.setText(data.phone)
        binding.tieEmail.setText(data.email)
        binding.radioGpGender.check(
            when (data.gender) {
                Gender.Male -> R.id.rBtnMale
                Gender.Female -> R.id.rBtnFemale
                Gender.Other -> R.id.rBtnOther
            }
        )
        binding.tieDescription.setText(data.description)
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
        fun getInstance() = UpdateProfileFragment()
    }

}