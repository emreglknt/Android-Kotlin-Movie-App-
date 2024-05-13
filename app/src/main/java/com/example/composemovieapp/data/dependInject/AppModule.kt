package com.example.composemovieapp.data.dependInject

import com.example.composemovieapp.data.remote.MovieAPI
import com.example.composemovieapp.data.repository.MovieRepoImplement
import com.example.composemovieapp.domain.repository.MovieRepo
import com.example.composemovieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi():MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI):MovieRepo{
        return MovieRepoImplement(api=api)
    }




}