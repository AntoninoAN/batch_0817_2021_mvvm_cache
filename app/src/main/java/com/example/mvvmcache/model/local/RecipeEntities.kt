package com.example.mvvmcache.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class RecipeEntities(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "recipe_name")
    val title: String,
    val image: String,
    val offset: Int
)

@Entity(tableName = "favorite_recipe")
data class RecipeFavorite(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "recipe_name")
    val title: String,
    val image: String,
    val extendedIngredients: String,
    val analyzedInstructions: String
)