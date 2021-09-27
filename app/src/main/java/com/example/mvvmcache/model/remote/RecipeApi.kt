package com.example.mvvmcache.model.remote

import com.example.mvvmcache.model.RecipeDetailsResponse
import com.example.mvvmcache.model.RecipeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("search")
        recipeName: String,
        @Query("apiKey")
        apiKey: String = "fb3e49784d1e48c880e2b5ae82ea90a8",
        @Query("offset")
        page: Int
    ): RecipeResponse
    @GET("recipes/{recipeId}/information")
    suspend fun getRecipeDetails(
        @Path("recipeId")
        recipeId: Int,
        @Query("apiKey")
        apiKey: String = "fb3e49784d1e48c880e2b5ae82ea90a8"
    ): RecipeDetailsResponse

    companion object{
        fun initRetrofit(): RecipeApi{
            return Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecipeApi::class.java)
        }
    }
}