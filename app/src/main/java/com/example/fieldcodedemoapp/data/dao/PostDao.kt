package com.example.fieldcodedemoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fieldcodedemoapp.data.model.Post

@Dao interface PostDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)
}