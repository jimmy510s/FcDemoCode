package com.example.fieldcodedemoapp.network


interface ServiceListener<R>
{
    fun onServerCallSucceeded(result: R)
    fun onServerCallFailed(errorMessage: String?,errorCode: Int)
}