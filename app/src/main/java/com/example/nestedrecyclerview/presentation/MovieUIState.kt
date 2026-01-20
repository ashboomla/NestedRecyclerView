package com.example.nestedrecyclerview.presentation

import android.graphics.Movie
import com.example.nestedrecyclerview.data.DTO.CategoryRow

sealed class MovieUIState {

    object loading : MovieUIState()
    data class success(val list:List<CategoryRow>): MovieUIState()
    data class Error(val message:String): MovieUIState()
}