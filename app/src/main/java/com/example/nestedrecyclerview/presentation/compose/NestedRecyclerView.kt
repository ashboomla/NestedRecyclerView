package com.example.nestedrecyclerview.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.nestedrecyclerview.domain.UIState
import com.example.nestedrecyclerview.presentation.MoviesViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun MainScreen(viewModel: MoviesViewModel) {

  val uiState = viewModel.moviesListState.collectAsState()


  Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
   ) {

    when (uiState) {
      UIState.Loading -> {
        CircularProgressIndicator()
        Text(text = "Error fetching data. Please try again later.")

      }

      is UIState.Error -> {
      }

      is UIState.Success -> {}
    }
  }

}

