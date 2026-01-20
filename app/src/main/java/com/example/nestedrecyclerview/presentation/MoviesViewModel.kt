package com.example.nestedrecyclerview.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nestedrecyclerview.data.MoviesRepoImpl
import com.example.nestedrecyclerview.domain.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
  val moviesRepoImpl =  MoviesRepoImpl()
  private var _moviesListState =  MutableStateFlow<UIState>(UIState.Loading)
  var moviesListState : StateFlow<UIState> = _moviesListState

  suspend fun getMoviesList() {

    _moviesListState.value = UIState.Loading

    viewModelScope.launch(Dispatchers.IO) {

      val response = moviesRepoImpl.getMoviesList()

      Log.d("AAAAAA_RESPONSE", "response: $response")
      if(response.isNotEmpty()) {
        _moviesListState.value = UIState.Success(response)
      }
    }
  }
}