package com.example.nestedrecyclerview

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.data.DataGenerator
import com.example.nestedrecyclerview.data.repository.CategoryRepositoryImpl
import com.example.nestedrecyclerview.domain.repository.usecases.MovieUseCase
import com.example.nestedrecyclerview.presentation.MovieUIState
import com.example.nestedrecyclerview.presentation.MovieViewModel
import com.example.nestedrecyclerview.presentation.MovieViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private lateinit var adapter: CategoryAdapter
  private var page = 0
  private var isLoading = false


  val repo = CategoryRepositoryImpl()
  val usecase = MovieUseCase(repo)
  val factory = MovieViewModelFactory(usecase)
  private lateinit var vm: MovieViewModel



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

      setupViewModel()  // initial load
  }

  private fun setupViewModel() {

    val vm = ViewModelProvider(this,factory).get(MovieViewModel::class.java)
    val pbar = findViewById<ProgressBar>(R.id.Pbar1)
    lifecycleScope.launch{

      vm.state.collect{
        when(it){
            is MovieUIState.Error -> pbar.visibility = View.GONE
            is MovieUIState.loading -> pbar.visibility = View.VISIBLE
            is MovieUIState.success -> {

              pbar.visibility = View.GONE

              adapter = CategoryAdapter {
                if (!isLoading) {
                  loadMore()
                }
              }
              val rv = findViewById<RecyclerView>(R.id.rvCategories)
              rv.layoutManager = LinearLayoutManager(this@MainActivity)
              rv.adapter = adapter
//              adapter.submitData(it.list)
              rv.setHasFixedSize(true)

              loadMore()


            }
        }



      }




    }
  }

  private fun loadMore() {
    isLoading = true

    val newData = DataGenerator.generateCategories(
      categoryCount = 10,
      moviesPerCategory = 50
    )

    adapter.submitData(newData)
    page++
    isLoading = false
  }


}


