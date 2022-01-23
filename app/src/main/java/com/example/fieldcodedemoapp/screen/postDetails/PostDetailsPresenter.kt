package com.example.fieldcodedemoapp.screen.postDetails

import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.network.ServiceListener
import com.example.fieldcodedemoapp.network.services.PostService

class PostDetailsPresenter(var view: PostDetailsContract.View?) : PostDetailsContract.Presenter {

    private lateinit var mPost: Post

    override fun detach() {
        view = null
    }

    override fun setData(post: Post) {
        mPost = post
        view?.setData(mPost)
    }

    override fun onOkClick(body: String) {

        if (body.isBlank()) {
            view?.showBodyError()
        } else {
            mPost.body = body
            uploadChanges()
        }
    }

    private fun uploadChanges() {
        view?.showLoading()

        PostService.updatePost(mPost, object : ServiceListener<Post> {
            override fun onServerCallSucceeded(result: Post) {
                view?.postUpdatedSuccessFully()
                view?.goBack()
            }

            override fun onServerCallFailed(errorMessage: String?, errorCode: Int) {
                view?.hideLoading()
                view?.handleServerError(errorMessage)
            }
        })
    }
}