package com.example.composemovieapp.domain.model

import com.example.composemovieapp.data.remote.dto.Rating

data class MovieDetailModel(
    val Actors: String?,
    val Country: String?,
    val Director: String?,
    val Genre: String?,
    val Plot: String?,
    val Poster: String?,
    val Rated: String?,
    val Released: String?,
    val Runtime: String?,
    val Title: String?,
    val Type: String?,
    val Year: String?,
    val imdbRating: String?,

)
