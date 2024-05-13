package com.example.composemovieapp.data.remote.dto

import com.example.composemovieapp.domain.model.MovieDetailModel

data class MovieDetailDto(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)


    //!!! DTO dan gelen üstteki verileri sadeleştirerek bizim istediğimiz ve  kullancağımızı belirlediğimiz verilerin olduğu modele indirger. !!!
    fun MovieDetailDto.toMovieDetailModel():MovieDetailModel{
        return  MovieDetailModel(Actors, Country, Director, Genre, Plot, Poster, Rated, Released, Runtime, Title, Type, Year, imdbRating)
    }