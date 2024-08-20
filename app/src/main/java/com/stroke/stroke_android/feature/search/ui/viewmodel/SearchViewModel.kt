package com.stroke.stroke_android.feature.search.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stroke.stroke_android.feature.home.ui.model.Post
import com.stroke.stroke_android.feature.search.data.repository.SearchRepository
import kotlinx.coroutines.launch

sealed class SearchUIModel {
    data object Loading : SearchUIModel()
    data class Success(val data: List<Post>) : SearchUIModel()
    data class Error(val errorMessage: String) : SearchUIModel()
    data object EmptyResult : SearchUIModel()
}

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _searchResultLiveData: MutableLiveData<SearchUIModel> = MutableLiveData()
    val searchResultLiveData: LiveData<SearchUIModel> get() = _searchResultLiveData

    fun search(keyword: String) {
        _searchResultLiveData.value = SearchUIModel.Loading
        viewModelScope.launch {
            repository.searchPosts(keyword).fold(
                {
                    _searchResultLiveData.value = if (it.isNotEmpty()) {
                        SearchUIModel.Success(it)
                    } else {
                        SearchUIModel.EmptyResult
                    }
                },
                {
                    _searchResultLiveData.value =
                        SearchUIModel.Error(it.message ?: "Something went wrong!")
                }
            )
        }
    }

}