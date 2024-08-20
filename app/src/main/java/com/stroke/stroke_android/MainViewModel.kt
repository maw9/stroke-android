package com.stroke.stroke_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stroke.stroke_android.feature.auth.data.repository.AuthRepository
import com.stroke.stroke_android.feature.auth.domain.model.AuthUser
import kotlinx.coroutines.launch

sealed class AuthUIState {
    data class Success(val data: AuthUser) : AuthUIState()
    data class Error(val error: Throwable) : AuthUIState()
}

class MainViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _authUserLiveData: MutableLiveData<AuthUIState> = MutableLiveData()
    val authUserLiveData: LiveData<AuthUIState> get() = _authUserLiveData

    private fun getCurrentAuthUser() {
        viewModelScope.launch {
            repository.getCurrentUser().fold(
                {
                    _authUserLiveData.value = AuthUIState.Success(it)
                },
                {
                    _authUserLiveData.value =
                        AuthUIState.Error(it)
                }
            )
        }
    }

    init {
        getCurrentAuthUser()
    }

}