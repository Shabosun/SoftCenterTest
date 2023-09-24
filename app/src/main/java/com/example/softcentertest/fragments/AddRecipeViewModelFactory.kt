package com.example.softcentertest.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.softcentertest.database.RecipeDao

class AddRecipeViewModelFactory(private val dao : RecipeDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddRecipeViewModel::class.java)){
            return AddRecipeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}