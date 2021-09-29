package com.example.mvvmcache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import com.example.mvvmcache.repository.Repository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @ExperimentalPagingApi
    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}