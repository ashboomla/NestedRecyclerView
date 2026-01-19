package com.example.nestedrecyclerview

import android.os.Bundle
import android.util.Log
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

        val rv = findViewById<RecyclerView>(R.id.rvCategories)
        adapter = CategoryAdapter {
            if (!isLoading) {
                loadMore(false , rv)
            }
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        rv.setHasFixedSize(true)

        loadMore(true, rv) // initial load
    }

    private fun loadMore(firstTime: Boolean, rv: RecyclerView) {
        isLoading = true

        val newData = DataGenerator.generateCategories(
            categoryCount = 10,
            moviesPerCategory = 50
        )

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