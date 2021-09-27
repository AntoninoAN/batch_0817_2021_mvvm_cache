package com.example.mvvmcache.repository

import android.content.Context
import androidx.core.content.edit
import com.example.mvvmcache.common.NetworkConnectivity
import com.example.mvvmcache.common.isConnected
import com.example.mvvmcache.model.remote.RecipeApi
import java.util.*

/**
 * Constructor Injection, to avoid hard coupling between classes
 */
class Repository(private val service: RecipeApi, private val context: Context): IRepository {

    companion object{
        const val CONNECTIVITY_SP = "connection_sp"
        const val DATE_REQUEST = "DATE_REQUEST"
    }

    override suspend fun useCaseRecipeResponse(recipeName: String): RecipeState {
        // todo first check network connectivity
        if (isConnected){
            // if connected, check cache availability
            //    if date in SP doenst exits, continue to remote
            //    if date in SP exits, compare if older>7days, continue to remote
            if (checkCacheAvailability()){
                // if cache good, return from cache.
                // todo query your Room DB.

            }else{
                // no cache available, do remote requests
                val response = service.getRecipes(recipeName)
                updateNewNetworkRequest()
                // todo insert in Room DB
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
    private fun checkCacheAvailability(): Boolean{
        val oldDateString = context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .getString(DATE_REQUEST, null) ?: return false

        val currentDate = Calendar.getInstance().timeInMillis - 604800000L

        return oldDateString.toLong() > currentDate
    }

    private fun updateNewNetworkRequest(){
        context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .edit {
                putString(DATE_REQUEST, Calendar.getInstance().timeInMillis.toString())
            }
    }
}