package com.example.mvvmcache.model

data class RecipeResponse(
    val results: List<Recipe>
)

data class Recipe(
    val id: Int,
    val title: String,
    val image: String
)

data class RecipeDetailsResponse(
    val extendedIngredients: List<Ingredients>,
    val title: String,
    val image: String,
    val summary: String
)

data class Ingredients(
    val name: String,
    val original: String
)