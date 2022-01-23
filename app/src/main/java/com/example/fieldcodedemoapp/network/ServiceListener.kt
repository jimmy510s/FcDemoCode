package com.example.fieldcodedemoapp.network

import com.example.fieldcodedemoapp.data.model.Post


interface ServiceListener<R>
{
    fun onServerCallSucceeded(result: ArrayList<Post>)
    fun onServerCallFailed(errorMessage: String?,errorCode: Int)
}