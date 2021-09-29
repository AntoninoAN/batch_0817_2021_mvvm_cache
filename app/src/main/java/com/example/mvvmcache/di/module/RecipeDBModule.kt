package com.example.mvvmcache.di.module

import android.content.Context
import com.example.mvvmcache.model.local.RecipeDB
import dagger.Module
import dagger.Provides

@Module
class RecipeDBModule {
    @Provides
    fun provideDatabase(context: Context): RecipeDB{
        return RecipeDB.recipeDatabaseInstance(context)
    }
}