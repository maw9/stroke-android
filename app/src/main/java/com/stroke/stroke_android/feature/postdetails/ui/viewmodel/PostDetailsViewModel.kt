package com.stroke.stroke_android.feature.postdetails.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stroke.stroke_android.feature.postdetails.data.repository.PostDetailsRepository
import com.stroke.stroke_android.feature.postdetails.ui.model.PostDetail
import kotlinx.coroutines.launch

sealed class PostDetailsUIState {
    data object Loading : PostDetailsUIState()
    data class Success(val data: PostDetail?) : PostDetailsUIState()
    data class Error(val errorMessage: String) : PostDetailsUIState()
}

class PostDetailsViewModel(private val repository: PostDetailsRepository) : ViewModel() {

    private val _detailsLiveData: MutableLiveData<PostDetailsUIState> = MutableLiveData()
    val detailsLiveData: LiveData<PostDetailsUIState> get() = _detailsLiveData

    var postImageUrl: String? = null

    fun getPostDetails(id: String) {
        _detailsLiveData.value = PostDetailsUIState.Loading
        viewModelScope.launch {
            repository.getPostDetails(id).fold(
                {
                    _detailsLiveData.value = PostDetailsUIState.Success(it)
                    postImageUrl = it?.imageUrl
                },
                {
                    _detailsLiveData.value =
                        PostDetailsUIState.Error(it.message ?: "Something went wrong!")
                }
            )
        }
    }

}