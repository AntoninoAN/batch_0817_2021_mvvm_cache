package com.example.mvvmcache.model.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.mvvmcache.model.local.RecipeDB
import com.example.mvvmcache.model.local.RecipeEntities
import com.example.mvvmcache.model.remote.RecipeApi
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class RecipeMediator(
    private val recipeName: String,
    private val recipeDB: RecipeDB,
    private val recipeApi: RecipeApi,
    private val isCacheStale: ()->Boolean
) : RemoteMediator<Int, RecipeEntities>() {

    /**
     * Invoked to execute a
     * 1.- network request
     * 2.- read from cache.
     * Obtains the last offset from remote
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeEntities>
    ): MediatorResult {
        // first validate last page fetched
        return try {
            val currentPage: Int = when (loadType) {
                // First time loading or force cache refresh
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastRecipePage: Int = state.lastItemOrNull()?.offset ?: 0

                    if (lastRecipePage == 50) return MediatorResult.Success(endOfPaginationReached = true)

                    lastRecipePage + 10
                }
            }
            val remoteResponse = recipeApi.getRecipes(
                recipeName = recipeName,
                page = currentPage
            )
            if (loadType == LoadType.REFRESH) {
                recipeDB.recipeDao().clearAllCache()

                recipeDB.recipeDao().insertAllCache(
                    remoteResponse.results.map {
                        RecipeEntities(
                            it.id,
                            it.title,
                            it.image,
                            currentPage
                        )
                    }
                )
            }
            MediatorResult.Success(endOfPaginationReached = currentPage == 50)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (isCacheStale()){
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
}