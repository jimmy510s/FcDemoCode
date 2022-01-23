package com.example.fieldcodedemoapp.data.db

import com.example.fieldcodedemoapp.data.model.Post
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class DbHelper {

    fun getPostsFromDb(): ArrayList<Post> {

        val postDao = AppDatabase.DatabaseBuilder.getInstance().postDao()
        val callable: Callable<List<Post>> = Callable{ postDao.getAllPosts() }
        val future: Future<List<Post>> = Executors.newSingleThreadExecutor().submit(callable)
        return ArrayList(future.get())
    }

    fun savePostToDb(post: Post) {

        Thread(Runnable {
            AppDatabase.DatabaseBuilder.getInstance().postDao().insertPost(post)
        }).start()
    }
}

