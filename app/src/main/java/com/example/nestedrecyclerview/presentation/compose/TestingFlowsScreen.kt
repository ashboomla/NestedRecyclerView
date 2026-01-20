package com.example.nestedrecyclerview.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nestedrecyclerview.domain.StateFlowState
import com.example.nestedrecyclerview.presentation.MoviesViewModel

@Composable
fun TestingFlowsScreen(modifier: Modifier = Modifier, viewModel: MoviesViewModel) {

    val flowsTesting =
        viewModel.stateFLowTesting.collectAsStateWithLifecycle(initialValue = StateFlowState.Started)

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        AnimatedVisibility(flowsTesting.value == StateFlowState.Started) {

            Text("I am in started state", style = MaterialTheme.typography.titleMedium)

        }

        AnimatedVisibility(flowsTesting.value == StateFlowState.Stopped) {

            Text("I am in stopped state", style = MaterialTheme.typography.titleMedium)

        }

    }


}