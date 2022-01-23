package com.example.fieldcodedemoapp.screen.postlist

import com.example.fieldcodedemoapp.data.model.Post


interface PostListScreenContract {

    // View
    interface View {
        fun showLoading()
        fun hideLoading()
        fun getPosts(posts: ArrayList<Post>)
        fun handleError(msg:String?)
    }

    // Presenter
    interface Presenter {
        fun getPosts()
        fun detach()
        fun updatePost(post:Post)
    }
}