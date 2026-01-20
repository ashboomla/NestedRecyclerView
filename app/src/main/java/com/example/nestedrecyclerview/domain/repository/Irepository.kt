package com.example.nestedrecyclerview.domain.repository

import com.example.nestedrecyclerview.data.DTO.CategoryRow

interface Irepository {

    suspend fun showMovies():List<CategoryRow>
}