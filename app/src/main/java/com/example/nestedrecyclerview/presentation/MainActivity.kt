package com.example.nestedrecyclerview.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.data.CategoryRow
import com.example.nestedrecyclerview.domain.UIState
import com.example.nestedrecyclerview.presentation.compose.MainScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  lateinit var viewModel: MoviesViewModel
  lateinit var adapter: CategoryAdapter
  private var page = 0
  var isLoading = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
//    setContentView(R.layout.activity_main)

    viewModel = MoviesViewModel()

    setContent{

      MainScreen(viewModel)
    }


    //oldNestedRecycler()
  }

  fun loadMore(firstTime: Boolean, rv: RecyclerView, items: List<CategoryRow>) {
    isLoading = true

    val newData = items

    if (firstTime) {
      adapter.submitData(newData)
    } else {
      rv.post {
        adapter.submitData(newData)
      }
    }
    page++
    isLoading = false
  }
}

private fun MainActivity.oldNestedRecycler() {
  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
    insets
  }

  val rv = findViewById<RecyclerView>(R.id.rvCategories)
  rv.layoutManager = LinearLayoutManager(this)
  rv.setHasFixedSize(true)

  viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)



  lifecycleScope.launch {
    viewModel.getMoviesList()

    repeatOnLifecycle(Lifecycle.State.STARTED) {
      viewModel.moviesListState.collect { state ->
        when (state) {
          is UIState.Error -> Toast.makeText(this@oldNestedRecycler, "Error", Toast.LENGTH_SHORT).show()
          UIState.Loading -> Toast.makeText(this@oldNestedRecycler, "Loading", Toast.LENGTH_SHORT).show()
          is UIState.Success -> {
            adapter = CategoryAdapter {
              if (!isLoading) {
                loadMore(false, rv, emptyList())
              }
            }
            rv.adapter = adapter
            loadMore(true, rv, state.items) // initial load
          }
        }
      }
    }
  }
}