package com.example.fieldcodedemoapp.screen.postlist

import com.example.fieldcodedemoapp.data.db.DbHelper
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.network.ServiceListener
import com.example.fieldcodedemoapp.network.services.PostService

class PostListScreenPresenter(var view: PostListScreenContract.View?):PostListScreenContract.Presenter {

    var dataList = ArrayList<Post>()
    var dbHelper = DbHelper()

    override fun getPosts() {

        view?.showLoading()

        PostService.getPosts(object: ServiceListener<ArrayList<Post>>{

            override fun onServerCallSucceeded(result: ArrayList<Post>) {

                for(post in result)
                    dbHelper.savePostToDb(post)

                view?.hideLoading()

                dataList.clear()
                dataList.addAll(result)
                view?.getPosts(dataList)
            }

            override fun onServerCallFailed(errorMessage: String?, errorCode: Int) {

                if(errorCode == PostService.NETWORK_ERROR){

                    val dbPosts = dbHelper.getPostsFromDb()
                    if(dbPosts.size > 0){
                        dataList.clear()
                        dataList.addAll(dbPosts)
                        view?.hideLoading()
                        view?.getPosts(dataList)
                    }else{
                        view?.hideLoading()
                        view?.handleError(errorMessage)
                    }
                }else{

                    view?.hideLoading()
                    view?.handleError(errorMessage)
                }
            }
        })
    }

    override fun detach() {
        view = null
    }

    override fun updatePost(post: Post) {
        dbHelper.savePostToDb(post)
    }
}