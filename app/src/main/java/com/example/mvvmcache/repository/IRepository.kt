package com.example.mvvmcache.repository

interface IRepository {
    suspend fun useCaseRecipeResponse(recipeName: String): RecipeState
    suspend fun useCaseRecipeDetails(recipeID: Int): RecipeState
}