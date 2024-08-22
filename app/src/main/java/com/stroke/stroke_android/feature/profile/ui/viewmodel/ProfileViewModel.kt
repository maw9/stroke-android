package com.stroke.stroke_android.feature.profile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stroke.stroke_android.feature.profile.data.repository.ProfileRepository
import com.stroke.stroke_android.feature.profile.ui.model.UserProfile
import kotlinx.coroutines.launch

sealed class ProfileUIState {
    data object Loading : ProfileUIState()
    data class Success(val data: UserProfile) : ProfileUIState()
    data class Error(val error: String) : ProfileUIState()
}

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _profileLiveData: MutableLiveData<ProfileUIState> = MutableLiveData()
    val profileLiveData: LiveData<ProfileUIState> get() = _profileLiveData

    private fun getProfile() {
        _profileLiveData.value = ProfileUIState.Loading
        viewModelScope.launch {
            profileRepository.getProfileByUid().fold(
                {
                    if (it != null) {
                        _profileLiveData.value = ProfileUIState.Success(it)
                    } else {
                        _profileLiveData.value =
                            ProfileUIState.Error("Error occurred in fetching user profile!")
                    }
                },
                {
                    _profileLiveData.value =
                        ProfileUIState.Error(it.message ?: "Something went wrong!")
                }
            )
        }
    }

    init {
        getProfile()
    }

}