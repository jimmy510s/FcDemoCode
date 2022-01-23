package com.example.fieldcodedemoapp.network.services

import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.misc.MyApplication
import com.example.fieldcodedemoapp.misc.Utils
import com.example.fieldcodedemoapp.network.Api
import com.example.fieldcodedemoapp.network.NetworkManager
import com.example.fieldcodedemoapp.network.NetworkManager.GENERAL_ERROR
import com.example.fieldcodedemoapp.network.NetworkManager.NETWORK_ERROR
import com.example.fieldcodedemoapp.network.ServiceListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService {

    companion object {

        fun getPosts(listener: ServiceListener<ArrayList<Post>>) {

            if (!Utils.isNetworkAvailable(MyApplication.appContext)) {
                listener.onServerCallFailed(
                    MyApplication.appContext.getString(R.string.no_internet_error),
                    NETWORK_ERROR
                )
                return
            }

            NetworkManager.retrofitService.getPosts().enqueue(object : Callback<ArrayList<Post>> {

                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {

                    if (response.body() != null)
                        listener.onServerCallSucceeded(response.body()!!)
                    else
                        listener.onServerCallFailed(null, GENERAL_ERROR);
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {

                    listener.onServerCallFailed(null, GENERAL_ERROR)
                }
            })
        }

        fun updatePost(post: Post, listener: ServiceListener<Post>) {

            if (!Utils.isNetworkAvailable(MyApplication.appContext)) {
                listener.onServerCallFailed(
                    MyApplication.appContext.getString(R.string.no_internet_error),
                    NETWORK_ERROR
                )
                return
            }

            NetworkManager.retrofitService.updatePost(post.id,post).enqueue(object : Callback<Post> {

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.body() != null)
                        listener.onServerCallSucceeded(response.body()!!)
                    else
                        listener.onServerCallFailed(null, GENERAL_ERROR);
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    listener.onServerCallFailed(null, GENERAL_ERROR)
                }
            })
        }
    }
}