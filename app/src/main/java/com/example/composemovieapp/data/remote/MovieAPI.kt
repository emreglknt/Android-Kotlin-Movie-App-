package com.example.composemovieapp.data.remote

import com.example.composemovieapp.data.remote.dto.MovieDetailDto
import com.example.composemovieapp.data.remote.dto.MoviesDto
import com.example.composemovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {


    //http://www.omdbapi.com/?i=tt3896198&apikey=74fb5bad
    //http://www.omdbapi.com/?s=batman&apikey=74fb5bad

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY
    ):MoviesDto


    @GET(".")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ):MovieDetailDto



}