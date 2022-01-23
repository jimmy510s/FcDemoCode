package com.example.fieldcodedemoapp.network

import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.network.services.PostService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface Api
{
    @GET(PostService.SERVICE_URL)
    fun getPosts() : Call<ArrayList<Post>>
}