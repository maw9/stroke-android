package com.stroke.stroke_android.feature.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stroke.stroke_android.feature.home.data.repository.HomePostsRepository
import com.stroke.stroke_android.feature.home.ui.model.Post
import kotlinx.coroutines.launch

sealed class HomePostsUIState {
    data object Loading : HomePostsUIState()
    data class Success(val posts: List<Post>) : HomePostsUIState()
    data class Error(val errorMessage: String) : HomePostsUIState()
}

class HomeViewModel(private val repository: HomePostsRepository) : ViewModel() {

    private val _postsLiveData: MutableLiveData<HomePostsUIState> = MutableLiveData()
    val postsLiveData: LiveData<HomePostsUIState> get() = _postsLiveData

    private fun getPosts() {
        _postsLiveData.value = HomePostsUIState.Loading
        viewModelScope.launch {
            repository.getPosts().fold(
                {
                    _postsLiveData.value = HomePostsUIState.Success(it)
                },
                {
                    _postsLiveData.value =
                        HomePostsUIState.Error(it.message ?: "Something went wrong!")
                }
            )
        }
    }

    init {
        getPosts()
    }

}