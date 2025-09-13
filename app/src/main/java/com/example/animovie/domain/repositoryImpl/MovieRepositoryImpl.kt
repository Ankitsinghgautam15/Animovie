package com.example.animovie.domain.repositoryImpl

import com.example.animovie.data.model.MovieResponse
import com.example.animovie.domain.RetroApi
import com.example.animovie.domain.repository.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: RetroApi) : MovieRepository {
    override suspend fun getMovies(page: Int): Response<MovieResponse> {
        return api.getMovies(page)
    }
}