package com.example.nestedrecyclerview.domain

import com.example.nestedrecyclerview.data.CategoryRow

interface MoviesRepo {

  fun getMoviesList() : List<CategoryRow>
}