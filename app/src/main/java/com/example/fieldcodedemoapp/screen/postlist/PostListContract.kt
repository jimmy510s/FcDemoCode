package com.example.fieldcodedemoapp.screen.postlist

import com.example.fieldcodedemoapp.data.model.Post


interface PostListContract {

    // View
    interface View {
        fun showLoading()
        fun hideLoading()
        fun goToDetailsScreen(post: Post)
        fun updateFav(pos:Int)
        fun getPosts(posts: ArrayList<Post>)
        fun handleError(msg:String?)
    }

    // Presenter
    interface Presenter {
        fun getPosts()
        fun onItemClick(post:Post)
        fun onFavClick(pos:Int)
        fun detach()
        fun updatePost(post:Post)
    }
}