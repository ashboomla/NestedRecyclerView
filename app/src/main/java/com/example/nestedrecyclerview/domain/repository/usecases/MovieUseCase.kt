package com.example.nestedrecyclerview.domain.repository.usecases

import com.example.nestedrecyclerview.data.DTO.CategoryRow
import com.example.nestedrecyclerview.domain.repository.Irepository

class MovieUseCase(
    private val repository: Irepository
) {

    suspend operator fun invoke(): List<CategoryRow> {
        return repository.showMovies()
    }
}