package com.example.fieldcodedemoapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManager {

    const val NETWORK_ERROR = -100
    const val GENERAL_ERROR = -1000
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofitService: Api by lazy { retrofit().create(Api::class.java) }

    private fun retrofit(): Retrofit {

        return Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create()).
        baseUrl(BASE_URL).build()
    }
}