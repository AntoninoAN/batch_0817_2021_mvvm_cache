package com.example.mvvmcache.model.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert(entity = RecipeEntities::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCache(remoteRecipes: List<RecipeEntities>)

    @Query("DELETE FROM recipe_table")
    suspend fun clearAllCache()

    @Query("SELECT * FROM recipe_table")
    fun pagingSource(): PagingSource<Int, RecipeEntities>
}