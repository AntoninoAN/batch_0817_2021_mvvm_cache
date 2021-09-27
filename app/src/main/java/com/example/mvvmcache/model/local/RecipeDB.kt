package com.example.mvvmcache.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeEntities::class, RecipeFavorite::class],
    version = 1, exportSchema = false
)
abstract class RecipeDB : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        private var INSTANCE: RecipeDB? = null

        fun recipeDatabaseInstance(context: Context): RecipeDB =
            INSTANCE ?: synchronized(this) {
                var temp = INSTANCE
                if (temp != null) return temp
                temp = Room.databaseBuilder(
                    context,
                    RecipeDB::class.java,
                    "recipe_database"
                )
                    .build()
                INSTANCE = temp
                return temp
            }
    }
}