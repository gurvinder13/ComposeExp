package com.example.composeexp.api

import com.example.composeexp.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZGYzMjE2YWE5ODExY2QyYWUzNzI4ODVlZWQzNDEyNCIsInN1YiI6IjVmMTVkZmEzMWQ3OGYyMDAzMzJhNzAxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GMjoM1FVZpqWEVXUCPoHv9uyRn1JmkBL3wkJRoiRC6s",
        "accept: application/json"
    )
    @GET("3/person/popular")
    suspend fun getPopularMovie(
        /* @Header("Authorization") header :String,
         @Header("accept") accept: String,*/
        @Query("language") language: String,
        @Query("page") pageNumber: Int,
    ): Response<MovieListResponse>
}
