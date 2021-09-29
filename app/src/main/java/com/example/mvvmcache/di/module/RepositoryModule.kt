package com.example.mvvmcache.di.module

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.example.mvvmcache.model.local.RecipeDB
import com.example.mvvmcache.model.paging.RecipeMediator
import com.example.mvvmcache.model.remote.RecipeApi
import com.example.mvvmcache.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @ExperimentalPagingApi
    @Provides
    fun provideRepository(
        service: RecipeApi,
        context: Context,
        remoteMediator: RecipeMediator,
        recipeDB: RecipeDB
    ): Repository {
        return Repository(
            service,
            context,
            remoteMediator,
            recipeDB
        )
    }
}