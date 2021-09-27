package com.example.mvvmcache.repository

import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun useCaseRecipeResponse(recipeName: String): Flow<RecipeState>
    suspend fun useCaseRecipeDetails(recipeID: Int): RecipeState
}