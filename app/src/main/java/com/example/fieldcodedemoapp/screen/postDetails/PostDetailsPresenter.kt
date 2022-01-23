package com.example.fieldcodedemoapp.screen.postDetails

import com.example.fieldcodedemoapp.data.model.Post

class PostDetailsPresenter(var view: PostDetailsContract.View?):PostDetailsContract.Presenter {

    private lateinit var mPost: Post

    override fun uploadChanges() {

    }

    override fun detach() {
        view = null
    }

    override fun getData(post: Post) {
        mPost = post
    }
}