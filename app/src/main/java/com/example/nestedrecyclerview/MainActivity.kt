package com.example.nestedrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.data.DataGenerator

class MainActivity : AppCompatActivity() {

  private lateinit var adapter: CategoryAdapter
  private var page = 0
  private var isLoading = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    adapter = CategoryAdapter {
      if (!isLoading) {
        loadMore()
      }
    }

    val rv = findViewById<RecyclerView>(R.id.rvCategories)
    rv.layoutManager = LinearLayoutManager(this)
    rv.adapter = adapter
    rv.setHasFixedSize(true)

    loadMore() // initial load
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