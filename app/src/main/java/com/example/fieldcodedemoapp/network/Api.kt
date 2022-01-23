package com.example.fieldcodedemoapp.network

import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.network.services.PostService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody
import retrofit2.http.*


interface Api
{
    @GET("posts")
    fun getPosts() : Call<ArrayList<Post>>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Long, @Body body: Post): Call<Post>
}