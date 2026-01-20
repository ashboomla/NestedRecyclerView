package com.example.nestedrecyclerview.presentation.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import coil.compose.AsyncImage
import com.example.nestedrecyclerview.data.CategoryRow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


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
          LoadMore = { })
      }

      is UIState.Error -> {
        Text(text = "Error")
      }

      UIState.Loading -> CircularProgressIndicator()

    }
  }


}

@Composable
fun CategoryList(categoryList: List<CategoryRow>, LoadMore: () -> Unit?) {


  LazyColumn {
    items(items = categoryList, key = { it.id }) { category ->

      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(category.title)
        CategoryRow(category)
      }
    }
  }
}

@Composable
fun CategoryRow(category: CategoryRow) {
  val scrollStateMap = rememberCategoryScrollStateMap()
  val rowState = scrollStateMap.getOrPut(category.id) {
    LazyListState()
  }

  LazyRow(state = rowState, contentPadding = PaddingValues(horizontal = 16.dp)) {
    items(category.movies, key = { it.id }) { item ->
      Box(Modifier.fillMaxSize()) {
        Text(category.title)
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

@Composable
fun rememberCategoryScrollStateMap():
  MutableMap<String, LazyListState> {
  return remember {
    mutableMapOf()
  }
}