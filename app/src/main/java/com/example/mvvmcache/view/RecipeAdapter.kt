package com.example.mvvmcache.view

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcache.model.local.RecipeEntities

class RecipeAdapter: PagingDataAdapter<RecipeEntities,
        RecipeAdapter.RecipeViewHolder>(RecipeDiffUtil) {

    object RecipeDiffUtil: DiffUtil.ItemCallback<RecipeEntities>(){
        override fun areItemsTheSame(oldItem: RecipeEntities,
                                     newItem: RecipeEntities): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeEntities,
                                        newItem: RecipeEntities): Boolean {
            return oldItem == newItem
        }

    }

    class RecipeViewHolder: RecyclerView.ViewHolder()

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.text = getItem(position).title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

    }
}