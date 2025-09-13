package com.example.animovie.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animovie.data.model.MovieResponse
import com.example.animovie.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val  repository: MovieRepository
): ViewModel() {
    var state  = MutableStateFlow<ScreenState>(ScreenState.Empty)

    init {
        viewModelScope.launch {
            state.value = ScreenState.Loading
            try {
                val response = repository.getMovies(1)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        state.value = ScreenState.Success(response)
                    }
                } else {
                    state.value = ScreenState.Error(response.errorBody().toString())
                   }
            } catch (e: Exception) {
                // Could log the exception or expose an error state
            }
        }
    }
}

sealed class ScreenState{
    data class Success(val movies: Response<MovieResponse>) : ScreenState()
    data class Error(val message: String) : ScreenState()
    object Loading : ScreenState()
    object Empty : ScreenState()
}