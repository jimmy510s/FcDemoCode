package com.example.fieldcodedemoapp.screen.postDetails

import com.example.fieldcodedemoapp.data.model.Post


interface PostDetailsContract {

    // View
    interface View {
        fun showLoading()
        fun hideLoading()
        fun setData(post: Post)
        fun handleError(msg:String?)
    }

    // Presenter
    interface Presenter {
        fun uploadChanges()
        fun detach()
        fun getData(post: Post)
    }
}