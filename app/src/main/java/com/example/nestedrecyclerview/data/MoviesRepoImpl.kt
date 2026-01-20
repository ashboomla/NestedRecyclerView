package com.example.nestedrecyclerview.data

import com.example.nestedrecyclerview.domain.MoviesRepo

class MoviesRepoImpl(): MoviesRepo {
  override fun getMoviesList(): List<CategoryRow> {
    return DataGenerator.generateCategories(
      categoryCount = 10,
      moviesPerCategory = 50
    )
  }
}