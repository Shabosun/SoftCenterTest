package com.example.softcentertest.retrofit

import retrofit2.Response
import retrofit2.http.GET


interface RecipeApi {

 @GET("recipes.json")
 suspend fun getAllRecipes() : Response<List<Recipe>>



}