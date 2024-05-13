package com.example.composemovieapp.data.repository

import com.example.composemovieapp.data.remote.MovieAPI
import com.example.composemovieapp.data.remote.dto.MovieDetailDto
import com.example.composemovieapp.data.remote.dto.MoviesDto
import com.example.composemovieapp.domain.repository.MovieRepo
import javax.inject.Inject

class MovieRepoImplement @Inject constructor(private val api:MovieAPI) :MovieRepo {

    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetails(imdbId=imdbId)
    }

}