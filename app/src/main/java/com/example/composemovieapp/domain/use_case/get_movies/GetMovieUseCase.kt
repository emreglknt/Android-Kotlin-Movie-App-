package com.example.composemovieapp.domain.use_case.get_movies

import com.example.composemovieapp.data.remote.dto.toMovieList
import com.example.composemovieapp.domain.model.MovieModel
import com.example.composemovieapp.domain.repository.MovieRepo
import com.example.composemovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repo:MovieRepo) {



    fun executeGetMovies(search: String): Flow<Resource<List<MovieModel>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repo.getMovies(search)
            if (movieList.Response=="True"){ // aranan kelime apida  valid bir film olarak varsa
                emit(Resource.Success(movieList.toMovieList()))
            }else{
                emit(Resource.Error(message = "No movie Found"))
            }
        }catch (e:IOError){
            emit(Resource.Error(message = "No Internet Connection"))
        }
    }

}