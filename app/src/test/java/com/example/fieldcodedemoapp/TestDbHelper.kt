package com.example.fieldcodedemoapp

import com.example.fieldcodedemoapp.data.dao.PostDao
import com.example.fieldcodedemoapp.data.model.Post
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class TestDbHelper {

    fun getPostsFromDb(dao:PostDao): ArrayList<Post> {

        val callable: Callable<List<Post>> = Callable{ dao.getAllPosts() }
        val future: Future<List<Post>> = Executors.newSingleThreadExecutor().submit(callable)
        return ArrayList(future.get())
    }

    fun savePostToDb(post: Post,dao:PostDao) {

        Thread(Runnable {
            dao.insertPost(post)
        }).start()
    }
}

