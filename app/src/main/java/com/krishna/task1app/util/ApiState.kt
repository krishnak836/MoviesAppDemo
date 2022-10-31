package com.krishna.task1app.util

import com.krishna.task1app.models.CategoriesModel
import com.krishna.task1app.models.CategoriesModelItem
import com.krishna.task1app.models.MoviesModel
import com.krishna.task1app.models.MoviesModelItem


sealed class CategoryApiState{
    object Loading : CategoryApiState()
    class Failure(val msg:Throwable) : CategoryApiState()
    class Success(val data:List<CategoriesModelItem>) : CategoryApiState()
    object Empty : CategoryApiState()
}
sealed class MovieApiState{
    object Loading : MovieApiState()
    class Failure(val msg:Throwable) : MovieApiState()
    class Success(val data:List<MoviesModelItem>) : MovieApiState()
    object Empty : MovieApiState()
}