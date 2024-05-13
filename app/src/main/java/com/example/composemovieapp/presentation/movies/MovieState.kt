package com.example.composemovieapp.presentation.movies

import com.example.composemovieapp.domain.model.MovieModel

data class MovieState(
    val isLoading : Boolean = false,
    val movies : List<MovieModel> = emptyList(),
    val error: String = "",
    val search: String = "batman"
)