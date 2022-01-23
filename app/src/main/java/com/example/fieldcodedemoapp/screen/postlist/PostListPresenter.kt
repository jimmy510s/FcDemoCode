package com.example.fieldcodedemoapp.screen.postlist

import com.example.fieldcodedemoapp.data.db.DbHelper
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.network.NetworkManager
import com.example.fieldcodedemoapp.network.ServiceListener
import com.example.fieldcodedemoapp.network.services.PostService

class PostListPresenter(var view: PostListContract.View?):PostListContract.Presenter {

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

                if(errorCode == NetworkManager.NETWORK_ERROR){

                    val dbPosts = dbHelper.getPostsFromDb()
                    if(dbPosts.size > 0){
                        dataList.clear()
                        dataList.addAll(dbPosts)
                        view?.hideLoading()
                        view?.getPosts(dataList)
                    }else{
                        view?.hideLoading()
                        view?.handleServerError(errorMessage)
                    }
                }else{

                    view?.hideLoading()
                    view?.handleServerError(errorMessage)
                }
            }
        })
    }

    override fun onItemClick(post: Post) {
        view?.goToDetailsScreen(post)
    }

    override fun onFavClick(pos: Int) {
        view?.updateFav(pos)
    }

    override fun detach() {
        view = null
    }

    override fun updatePost(post: Post) {
        dbHelper.savePostToDb(post)
    }
}