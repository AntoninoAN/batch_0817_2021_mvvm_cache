package com.example.mvvmcache.repository

import androidx.paging.PagingData
import com.example.mvvmcache.model.Recipe
import com.example.mvvmcache.model.RecipeDetailsResponse
import com.example.mvvmcache.model.RecipeResponse
import com.example.mvvmcache.model.local.RecipeEntities

sealed class RecipeState{
    data class ResponseRecipes(val data: PagingData<RecipeEntities>): RecipeState()
    data class ResponseRecipeDetails(val data: RecipeDetailsResponse): RecipeState()
    data class Error(val errorMessage: String, val throwable: Throwable?): RecipeState()
    data class Loading(val isLoading: Boolean): RecipeState()
}
