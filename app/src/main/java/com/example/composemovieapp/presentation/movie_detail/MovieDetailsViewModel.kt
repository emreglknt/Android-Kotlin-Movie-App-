package com.example.composemovieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.example.composemovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.example.composemovieapp.presentation.movies.MovieState
import com.example.composemovieapp.util.Constants.IMDB_ID
import com.example.composemovieapp.util.Resource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.PrimitiveIterator
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase ,// repoyu provide ettiğimiz için bu da sadce repoyu dependency olarak alıyor. Bunu provide etmeye gerek yok
    private val stateHandle: SavedStateHandle
) : ViewModel() {




    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state


        init {
            stateHandle.get<String>(IMDB_ID)?.let {
                getMovieDetails(it)
            }
        }



    private fun getMovieDetails(imdbId:String){

         getMovieDetailsUseCase.executeGetMovieDetails(imdbId=imdbId).onEach {

            when(it){
                is Resource.Success->{
                    _state.value = MovieDetailState(movie=it.data)
                }
                is Resource.Error->{
                    _state.value = MovieDetailState(error =it.message ?: "Error")
                }
                is Resource.Loading->{
                    _state.value = MovieDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }



}