package com.krishna.task1app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishna.task1app.repository.MainRepository
import com.krishna.task1app.util.CategoryApiState
import com.krishna.task1app.util.MovieApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow: MutableLiveData<CategoryApiState> =
        MutableLiveData(CategoryApiState.Empty)

    val categoryFlowObserver: LiveData<CategoryApiState> = postStateFlow

    fun getCategory() = viewModelScope.launch {
        postStateFlow.value = CategoryApiState.Loading
        mainRepository.getCategories()
            .catch { e ->
                postStateFlow.value = CategoryApiState.Failure(e)
            }.collect { data ->
                postStateFlow.value = CategoryApiState.Success(data)
            }
    }

    private val movieStateFlow: MutableLiveData<MovieApiState> =
        MutableLiveData(MovieApiState.Empty)

    val movieFlowObserver: LiveData<MovieApiState> = movieStateFlow

    fun getMovies() = viewModelScope.launch {
        movieStateFlow.value = MovieApiState.Loading
        mainRepository.getMovies()
            .catch { e ->
                movieStateFlow.value = MovieApiState.Failure(e)
            }.collect { data ->
                movieStateFlow.value = MovieApiState.Success(data)
            }
    }
}