package com.krishna.task1app.network

import com.krishna.task1app.models.CategoriesModel
import com.krishna.task1app.models.CategoriesModelItem
import com.krishna.task1app.models.MoviesModel
import com.krishna.task1app.models.MoviesModelItem
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getCategories(): List<CategoriesModelItem> =
        apiService.getCategories("test", "test1", "get_vod_categories")

    suspend fun getMovies(): List<MoviesModelItem> =
        apiService.getMovies("test", "test1", "get_vod_streams")
}