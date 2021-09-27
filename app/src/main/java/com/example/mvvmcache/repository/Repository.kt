package com.example.mvvmcache.repository

import android.content.Context
import androidx.core.content.edit
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mvvmcache.common.isConnected
import com.example.mvvmcache.model.local.RecipeDB
import com.example.mvvmcache.model.local.RecipeEntities
import com.example.mvvmcache.model.paging.RecipeMediator
import com.example.mvvmcache.model.remote.RecipeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

/**
 * Constructor Injection, to avoid hard coupling between classes
 */
@ExperimentalPagingApi
class Repository  constructor(
    private val service: RecipeApi,
    private val context: Context,
    private val remoteMediator: RecipeMediator,
    private val recipeDB: RecipeDB
) : IRepository {

    companion object {
        const val CONNECTIVITY_SP = "connection_sp"
        const val DATE_REQUEST = "DATE_REQUEST"
    }

    fun createPager(): Pager<Int, RecipeEntities>{
        return Pager(
            config = PagingConfig(10),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { recipeDB.recipeDao().pagingSource() }
        )
    }

    override suspend fun useCaseRecipeResponse(recipeName: String): Flow<RecipeState> {
        // todo first check network connectivity
       return if (isConnected) {
            // if connected, check cache availability
            //    if date in SP doenst exits, continue to remote
            //    if date in SP exits, compare if older>7days, continue to remote
            createPager().flow
                .map {page->
                    RecipeState.ResponseRecipes(page)
                }
//            if (checkCacheAvailability()) {
//                // if cache good, return from cache.
//                // todo query your Room DB.
//
//            } else {
//                // no cache available, do remote requests
//                val response = service.getRecipes(recipeName)
//                updateNewNetworkRequest()
//                // todo insert in Room DB
//            }
        }else{
           createPager().flow
               .map {page->
                   RecipeState.ResponseRecipes(page)
               }
        }
    }

    override suspend fun useCaseRecipeDetails(recipeID: Int): RecipeState {
        TODO("Not yet implemented")
    }

    /**
     * Check if Date in SP is valid or not
     * Is valid if date < 7 days
     */
    private fun checkCacheAvailability(): Boolean {
        val oldDateString = context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .getString(DATE_REQUEST, null) ?: return false

        val currentDate = Calendar.getInstance().timeInMillis - 604800000L

        return oldDateString.toLong() > currentDate
    }

    private fun updateNewNetworkRequest() {
        context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .edit {
                putString(DATE_REQUEST, Calendar.getInstance().timeInMillis.toString())
            }
    }
}