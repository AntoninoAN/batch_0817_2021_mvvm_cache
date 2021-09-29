package com.example.mvvmcache.di.module

import androidx.paging.ExperimentalPagingApi
import com.example.mvvmcache.model.local.RecipeDB
import com.example.mvvmcache.model.paging.RecipeMediator
import com.example.mvvmcache.model.remote.RecipeApi
import dagger.Module
import dagger.Provides

@Module
class RemoteMediatorModule {
    @ExperimentalPagingApi
    @Provides
    fun provideRemoteMediator(
        recipeDB: RecipeDB,
        recipeApi: RecipeApi,
        callBack: () -> Boolean,
        query: String
    ): RecipeMediator {
        return RecipeMediator(
            recipeDB = recipeDB,
            recipeApi = recipeApi,
            isCacheStale = callBack,
            recipeName = query
        )
    }
}