package com.example.nestedrecyclerview.presentation.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.nestedrecyclerview.domain.UIState
import com.example.nestedrecyclerview.presentation.MoviesViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@SuppressLint("SuspiciousIndentation")
@Composable
fun MainScreen(viewModel: MoviesViewModel) {
    val uiState = viewModel.moviesListState.collectAsState().value

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when(val state = uiState) {
            is UIState.Success -> {
                Text(text = state.items[0].title)
            }
            is UIState.Error -> TODO()
            UIState.Loading -> TODO()
        }
    }


}

