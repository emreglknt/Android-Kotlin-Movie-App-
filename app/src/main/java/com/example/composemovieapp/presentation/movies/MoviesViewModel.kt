package com.example.composemovieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.example.composemovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase:GetMovieUseCase // repoyu provide ettiğimiz için bu da sadce repoyu dependency olarak alıyor. Bunu provide etmeye gerek yok.
) :ViewModel() {



    private val _state = mutableStateOf<MovieState>(MovieState())
    val state : State<MovieState> = _state

    init {  // en başta ekranda
        // Movie statetteki search değerinde yazan spiderman aranarak getirilir default olarak
        getMovies(_state.value.search)
    }

    private var job : Job? = null // kullanıcı işlem bitmeden arama yapmak isterse

    // movielerin gelmesi ekranında success error ve loading durumlarında
    // state in değerini günceller.
    private fun getMovies(search: String) {
        viewModelScope.launch {
            getMovieUseCase.executeGetMovies(search).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = MovieState(movies = it.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = MovieState(error = it.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        _state.value = MovieState(isLoading = true)
                    }
                }
            }
        }
    }
    //kullanıcının search etkileşim eventini alıp
    //içindeki search değeriyle beraber üstteki getMovies fonksiyonuna gönderir.

    fun onEvent(event: MovieEvent) {
        when (event) {
            is MovieEvent.Search -> {
                debounceSearch(event.searchString)
            }
        }
    }

    private fun debounceSearch(search: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(300) // Kullanıcının yazma hızını dikkate alarak 300ms gecikme ekleyerek debounce etkisi sağlıyoruz
            getMovies(search)
        }
    }


}