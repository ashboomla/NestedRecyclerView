package com.example.nestedrecyclerview.data.DTO

data class CategoryRow(
    val id: String,
    val title: String,
    val movies: List<MovieItem>,
    var scrollPosition: Int = 0
)

