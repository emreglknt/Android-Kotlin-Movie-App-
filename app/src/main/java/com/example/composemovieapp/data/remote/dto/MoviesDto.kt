package com.example.composemovieapp.data.remote.dto

import com.example.composemovieapp.domain.model.MovieModel

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)



        // serach Listesinden gelen filmlerin hepsini tek tek alarak
        // moviemodel sınıfındaki paramerelere objeler oluşturuyoruz.
        fun MoviesDto.toMovieList():List<MovieModel>{
            return Search.map { search -> MovieModel(search.Poster,search.Title,search.Year,search.imdbID) }
        }
