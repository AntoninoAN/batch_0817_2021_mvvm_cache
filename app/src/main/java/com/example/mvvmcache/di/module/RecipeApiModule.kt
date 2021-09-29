package com.example.mvvmcache.di.module

import com.example.mvvmcache.model.remote.RecipeApi
import dagger.Module
import dagger.Provides

@Module
class RecipeApiModule {

    @Provides
    fun provideRecipeApi(): RecipeApi = RecipeApi.initRetrofit()
}