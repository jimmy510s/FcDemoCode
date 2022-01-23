package com.example.fieldcodedemoapp.screen.postDetails

import com.example.fieldcodedemoapp.data.model.Post


interface PostDetailsContract {

    // View
    interface View {
        fun showLoading()
        fun hideLoading()
        fun setData(post: Post)
        fun showBodyError()
        fun handleServerError(msg:String?)
        fun postUpdatedSuccessFully()
        fun goBack()
    }

    // Presenter
    interface Presenter {
        fun detach()
        fun setData(post: Post)
        fun onOkClick(body:String)
    }
}