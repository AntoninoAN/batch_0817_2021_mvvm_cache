package com.example.mvvmcache.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule {
    private lateinit var recipeContext: Context

    @Provides
    fun provideContext(context: Context) : Context{
        recipeContext = context
        return recipeContext
    }
}