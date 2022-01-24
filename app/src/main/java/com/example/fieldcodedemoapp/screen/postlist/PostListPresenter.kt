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

        //show the loading first
        view?.showLoading()

        //get the data from the db
        dataList.clear()
        dataList.addAll(dbHelper.getPostsFromDb())
        view?.getPosts()

        //get the data from server, update on db and display the updated data
        PostService.getPosts(object: ServiceListener<ArrayList<Post>>{

            override fun onServerCallSucceeded(result: ArrayList<Post>) {

                for(post in result)
                    dbHelper.savePostToDb(post)

                view?.hideLoading()

                dataList.clear()
                dataList.addAll(result)
                view?.getPosts()
            }

            override fun onServerCallFailed(errorMessage: String?, errorCode: Int) {

                //if we get an error but we have local data do nothing. we can add bussiness here comparing last update time
                //and show user a snackbar or something etc
                view?.hideLoading()
                if(dataList.isEmpty())
                    view?.handleServerError(errorMessage)
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