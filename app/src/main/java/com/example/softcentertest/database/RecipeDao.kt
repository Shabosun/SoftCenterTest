package com.example.softcentertest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.softcentertest.retrofit.Recipe

@Dao
interface RecipeDao {

    @Insert
    fun insert(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(listRecipe: List<Recipe>)

    @Query("SELECT * FROM recipe_table")
     fun getAll(): List<Recipe>
}