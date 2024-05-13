package com.example.composemovieapp.domain.use_case.get_movie_detail

import com.example.composemovieapp.data.remote.dto.toMovieDetailModel
import com.example.composemovieapp.domain.model.MovieDetailModel
import com.example.composemovieapp.domain.repository.MovieRepo
import com.example.composemovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: MovieRepo) {


    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetailModel>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repo.getMovieDetail(imdbId=imdbId)
            emit(Resource.Success(movieDetail.toMovieDetailModel()))
        }catch (e: IOError){
            emit(Resource.Error(message = "No Internet Connection"))
        }
    }


}