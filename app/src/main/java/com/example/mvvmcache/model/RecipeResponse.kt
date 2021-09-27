package com.example.mvvmcache.model

data class RecipeResponse(
    val results: List<Recipe>,
    val offset: Int
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
    val summary: String,
    val analyzedInstructions: List<Instructions>
)

data class Instructions(
    val steps: List<Step>
)

data class Step(
    val number: Int,
    val step: String
)

data class Ingredients(
    val name: String,
    val original: String
)