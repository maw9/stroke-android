package com.stroke.stroke_android.feature.profile.ui.viewmodel

import android.content.SharedPreferences
import android.net.Uri
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stroke.stroke_android.R
import com.stroke.stroke_android.feature.auth.data.repository.AuthRepository
import com.stroke.stroke_android.feature.auth.domain.model.asUserProfile
import com.stroke.stroke_android.feature.profile.data.repository.ProfileRepository
import com.stroke.stroke_android.feature.profile.ui.model.Gender
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile
import kotlinx.coroutines.launch

sealed class UpdateProfileUIState {
    data object Loading : UpdateProfileUIState()
    data class Success(val data: String) : UpdateProfileUIState()
    data class Error(val error: String) : UpdateProfileUIState()
    data object FetchProfileDone : UpdateProfileUIState()
}

class UpdateProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var isProfileFirstTimeFetch = true

    fun doneProfileFetching() {
        isProfileFirstTimeFetch = false
    }

    private val _updateProfileLiveData: MutableLiveData<UpdateProfileUIState> = MutableLiveData()
    val updateProfileLiveData: LiveData<UpdateProfileUIState> get() = _updateProfileLiveData

    private val _profileDataLiveData: MutableLiveData<UserProfile> = MutableLiveData()
    val profileDataLiveData: LiveData<UserProfile> get() = _profileDataLiveData

    private val _photoLiveData: MutableLiveData<Uri> = MutableLiveData()
    val photoLiveData: LiveData<Uri> get() = _photoLiveData

    fun onPhotoPick(uri: Uri) {
        _photoLiveData.value = uri
    }

    fun onNameChange(name: String) {
        _profileDataLiveData.value = _profileDataLiveData.value?.copy(name = name)
    }

    fun onPhoneChange(phone: String) {
        _profileDataLiveData.value = _profileDataLiveData.value?.copy(phone = phone)
    }

    fun onEmailChange(email: String) {
        _profileDataLiveData.value = _profileDataLiveData.value?.copy(email = email)
    }

    fun onGenderChange(checkedId: Int) {
        _profileDataLiveData.value = _profileDataLiveData.value?.copy(
            gender =
            when (checkedId) {
                R.id.rBtnMale -> Gender.Male
                R.id.rBtnFemale -> Gender.Female
                R.id.rBtnOther -> Gender.Other
                else -> Gender.Male
            }
        )
    }

    fun onDescriptionChange(description: String) {
        _profileDataLiveData.value = _profileDataLiveData.value?.copy(description = description)
    }

    private fun getProfileFromAuth() {
        _updateProfileLiveData.value = UpdateProfileUIState.Loading
        viewModelScope.launch {
            authRepository.getCurrentUser().fold(
                {
                    _profileDataLiveData.value = it.asUserProfile()
                    _updateProfileLiveData.value = UpdateProfileUIState.FetchProfileDone
                },
                {
                    _updateProfileLiveData.value = UpdateProfileUIState.Error(
                        "Failed to fetch user data!"
                    )
                }
            )
        }
    }

    private fun getProfile() {
        _updateProfileLiveData.value = UpdateProfileUIState.Loading
        viewModelScope.launch {
            profileRepository.getProfileByUid().fold(
                {
                    _profileDataLiveData.value = it!!
                    _updateProfileLiveData.value = UpdateProfileUIState.FetchProfileDone
                },
                {
                    _updateProfileLiveData.value = UpdateProfileUIState.Error(
                        "Failed to fetch user data!"
                    )
                }
            )
        }
    }

    fun saveProfile() {
        _updateProfileLiveData.value = UpdateProfileUIState.Loading
        viewModelScope.launch {
            _profileDataLiveData.value?.let { profile ->
                profileRepository.saveProfile(profile).fold(
                    {
                        sharedPreferences.edit(commit = true) {
                            putBoolean("is_profile_data_filled", true)
                        }
                        _updateProfileLiveData.value = UpdateProfileUIState.Success(it)
                    },
                    {
                        _updateProfileLiveData.value =
                            UpdateProfileUIState.Error(it.message ?: "Something went wrong!")
                    }
                )
            }
        }
    }

    init {
        if (sharedPreferences.getBoolean("is_profile_data_filled", false)) {
            getProfile()
        } else {
            getProfileFromAuth()
        }
    }

}