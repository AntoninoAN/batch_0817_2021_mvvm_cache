package com.example.mvvmcache.repository

import com.example.mvvmcache.model.RecipeDetailsResponse
import com.example.mvvmcache.model.RecipeResponse

sealed class RecipeState{
    data class ResponseRecipes(val data: RecipeResponse): RecipeState()
    data class ResponseRecipeDetails(val data: RecipeDetailsResponse): RecipeState()
    data class Error(val errorMessage: String, val throwable: Throwable?): RecipeState()
    data class Loading(val isLoading: Boolean): RecipeState()
}
