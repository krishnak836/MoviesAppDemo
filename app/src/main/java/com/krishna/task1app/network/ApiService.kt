package com.krishna.task1app.network

import com.krishna.task1app.models.CategoriesModel
import com.krishna.task1app.models.CategoriesModelItem
import com.krishna.task1app.models.MoviesModel
import com.krishna.task1app.models.MoviesModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("player_api.php")
    suspend fun getCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String,
    ): List<CategoriesModelItem>

    @GET("player_api.php")
    suspend fun getMovies(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String,
    ): List<MoviesModelItem>
}