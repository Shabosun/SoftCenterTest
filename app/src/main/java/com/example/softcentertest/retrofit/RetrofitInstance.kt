package com.example.softcentertest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//объект для инициализации retrofit
object RetrofitInstance {

    private const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"
    private val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    fun <T> create(service: Class<T>) : T{
        return retrofit.create(service)
    }
}