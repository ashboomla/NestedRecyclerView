package com.example.nestedrecyclerview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nestedrecyclerview.data.DTO.CategoryRow
import com.example.nestedrecyclerview.domain.repository.Irepository
import com.example.nestedrecyclerview.domain.repository.usecases.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MovieUseCase): ViewModel() {

    private val _state = MutableStateFlow<MovieUIState>(MovieUIState.loading)
    val state = _state


    fun showMovies(){

        _state.value = MovieUIState.loading

        viewModelScope.launch{

            val result = useCase()
            if(result!=null){

               _state.value =  MovieUIState.success(result)

            } else{

                _state.value = MovieUIState.Error("Data is emoty")

            }



        }



    }


}

class MovieViewModelFactory(private val useCase: MovieUseCase): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(useCase) as T
    }


}

