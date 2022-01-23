package com.example.fieldcodedemoapp.screen.postDetails

import com.example.fieldcodedemoapp.data.db.DbHelper
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.network.ServiceListener
import com.example.fieldcodedemoapp.network.services.PostService

class PostDetailsPresenter(var view: PostDetailsContract.View?):PostDetailsContract.Presenter {

    private lateinit var mPost: Post

    override fun uploadChanges() {

    }

    override fun detach() {

    }
}