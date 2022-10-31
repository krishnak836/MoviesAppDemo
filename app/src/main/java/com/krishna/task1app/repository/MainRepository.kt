package com.krishna.task1app.repository

import com.krishna.task1app.models.CategoriesModel
import com.krishna.task1app.models.CategoriesModelItem
import com.krishna.task1app.models.MoviesModel
import com.krishna.task1app.models.MoviesModelItem
import com.krishna.task1app.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getCategories(): Flow<List<CategoriesModelItem>> = flow {
        emit(apiServiceImpl.getCategories())
    }.flowOn(Dispatchers.IO)

    fun getMovies(): Flow<List<MoviesModelItem>> = flow {
        emit(apiServiceImpl.getMovies())
    }.flowOn(Dispatchers.IO)

}