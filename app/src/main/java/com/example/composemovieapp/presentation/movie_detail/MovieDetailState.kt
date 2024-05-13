package com.example.composemovieapp.presentation.movie_detail

import coil.compose.ImagePainter
import com.example.composemovieapp.domain.model.MovieDetailModel
import com.example.composemovieapp.util.Resource

data class MovieDetailState(
    val isLoading: Boolean=false,
    val movie : MovieDetailModel?=null,
    val error: String = ""
)