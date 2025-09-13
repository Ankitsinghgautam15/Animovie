package com.example.animovie.domain

import com.example.animovie.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroApi {

    @GET("movies")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MovieResponse>
}