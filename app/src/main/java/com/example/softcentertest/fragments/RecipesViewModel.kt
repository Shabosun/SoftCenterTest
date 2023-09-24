package com.example.softcentertest.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.softcentertest.database.RecipeDao
import com.example.softcentertest.retrofit.Recipe
import com.example.softcentertest.retrofit.RecipeApi
import com.example.softcentertest.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

//dao нужен для взаимодействия с БД, context для проверки подключения к интернету
class RecipesViewModel(val dao : RecipeDao, val context: Context) : ViewModel() {


    private val api = RetrofitInstance.create(RecipeApi::class.java) //инициализируем api для работы с Retrofit
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _navigateToRecipe = MutableLiveData<Recipe?>()
    val navigateToRecipe: LiveData<Recipe?>
        get() = _navigateToRecipe




    fun onRecipeClicked(recipe: Recipe) {
        _navigateToRecipe.value = recipe
    }

    fun onRecipeNavigated() {
        _navigateToRecipe.value = null
    }



    fun getRecipes() {
        if (isInternetAvailable() == true) // если есть подключение к интернету, то берем данные с сервера
        {
            viewModelScope.launch {
                val response = api.getAllRecipes()
                if (response.isSuccessful) {
                    _recipes.value = response.body()
                    response.body()?.let { saveListToDatabase(it) } //кэшируем данные в БД
                } else {


                }
            }
        }
        else{ //если интернета нет, берем данные с БД
            viewModelScope.launch {
                _recipes.value = dao.getAll()
            }

        }

    }

    fun saveListToDatabase(list : List<Recipe>){
        viewModelScope.launch {
            dao.insertAll(list)
        }
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }





}