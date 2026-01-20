package com.example.nestedrecyclerview.presentation.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.nestedrecyclerview.domain.UIState
import com.example.nestedrecyclerview.presentation.MoviesViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nestedrecyclerview.data.CategoryRow


@SuppressLint("SuspiciousIndentation")
@Composable
fun MainScreen(viewModel: MoviesViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getMoviesList()
    }
    val uiState = viewModel.moviesListState.collectAsState().value

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val state = uiState) {
            is UIState.Success -> {
                CategoryList(
                    categoryList = state.items,
                    loadMore = { }, viewModel
                )
            }

            is UIState.Error -> {
                Text(text = "Error")
            }

            UIState.Loading -> CircularProgressIndicator()

        }
    }


}

@Composable
fun CategoryList(
    categoryList: List<CategoryRow>,
    loadMore: () -> Unit?,
    viewModel: MoviesViewModel
) {
    LazyColumn {
        items(items = categoryList, key = { it.id }) { category ->
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CategoryRow(category = category, state = viewModel.getRowState(category.id))
            }
        }
//        item {
//            LaunchedEffect(Unit) {
//                loadMore()
//            }
//        }

    }
}

@Composable
fun CategoryRow(category: CategoryRow, state: LazyListState) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = category.title, style = MaterialTheme.typography.titleMedium)
        LazyRow(
            state = state,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(category.movies, key = { it.id }) { item ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        model = item.posterUrl,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }
            }
        }
    }


}

@Composable
fun rememberCategoryScrollStateMap():
        MutableMap<String, LazyListState> {
    return remember {
        mutableMapOf()
    }
}