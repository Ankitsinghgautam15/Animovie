package com.example.animovie.domain.repository

import com.example.animovie.data.model.MovieResponse
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovies(page: Int): Response<MovieResponse>
}