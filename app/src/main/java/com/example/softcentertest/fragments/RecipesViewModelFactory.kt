package com.example.softcentertest.fragments

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.softcentertest.database.RecipeDao

class RecipesViewModelFactory(private val dao : RecipeDao, private val context : Context)
    :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)){
            return RecipesViewModel(dao, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}