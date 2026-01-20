package com.example.nestedrecyclerview.domain

import com.example.nestedrecyclerview.data.CategoryRow

sealed class UIState {
  data class Success(val items: List<CategoryRow>): UIState()
  data class Error(val errorMsg: String): UIState()
  object Loading: UIState()
}