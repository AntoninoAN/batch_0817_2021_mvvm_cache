package com.example.mvvmcache.di

import com.example.mvvmcache.MainActivity
import com.example.mvvmcache.di.module.*
import dagger.Component

@Component(
    modules = [ContextModule::class,
        RecipeApiModule::class,
        RecipeDBModule::class,
        RemoteMediatorModule::class,
        RepositoryModule::class]
)
interface RecipeComponent {
    fun inject(activity: MainActivity)
}