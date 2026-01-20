package com.example.nestedrecyclerview.data.repository

import com.example.nestedrecyclerview.data.DTO.CategoryRow
import com.example.nestedrecyclerview.data.DataGenerator
import com.example.nestedrecyclerview.domain.repository.Irepository

class CategoryRepositoryImpl : Irepository {
    override suspend fun showMovies(): List<CategoryRow> {
      return  DataGenerator.generateCategories()
    }


}