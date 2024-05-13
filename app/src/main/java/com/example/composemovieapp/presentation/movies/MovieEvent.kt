package com.example.composemovieapp.presentation.movies

sealed class MovieEvent {
    data class Search(val searchString:String):MovieEvent()


}