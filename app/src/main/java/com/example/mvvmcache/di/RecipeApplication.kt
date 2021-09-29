package com.example.mvvmcache.di

import android.app.Application

class RecipeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //component = DaggerRecipeComponent
    }

    companion object{
        //lateinit var component: DaggerRecipeComponent
    }
}