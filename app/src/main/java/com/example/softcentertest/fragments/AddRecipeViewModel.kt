package com.example.softcentertest.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.softcentertest.database.RecipeDao
import com.example.softcentertest.retrofit.Recipe
import kotlinx.coroutines.launch

class AddRecipeViewModel(private val dao : RecipeDao) : ViewModel() {


    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList


    fun addRecipe(calories : String,
                  carbos : String,
                  description : String,
                   difficulty : Int,
                  fats : String,
                  headline : String,
                  id : String,
                  image : String,
                  name : String,
                  proteins : String,
                  thumb : String,
                  time : String)
    {
        viewModelScope.launch {
            val recipe = Recipe(calories, carbos, description , difficulty, fats, headline, id, image, name, proteins, thumb, time )
            dao.insert(recipe!!)
            _navigateToList.value = true

        }
    }

    fun onNavigatedToList()
    {
        _navigateToList.value = false
    }


}