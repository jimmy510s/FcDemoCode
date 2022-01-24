package com.example.fieldcodedemoapp.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fieldcodedemoapp.data.dao.PostDao
import com.example.fieldcodedemoapp.data.model.Post
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@Config(manifest= Config.NONE)
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest: TestCase() {

    private lateinit var dao: PostDao
    private lateinit var db: AppDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        dao = db.postDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writePostsAndRetrieveThem() {

        val listToWrite = ArrayList<Post>()
        val post1 = Post(1L,"TestTitle1","TestBody1",false)
        val post2 = Post(1L,"TestTitle2","TestBody2",false)
        val post3 = Post(1L,"TestTitle3","TestBody3",false)
        listToWrite.add(post1)
        listToWrite.add(post2)
        listToWrite.add(post3)


        for(post in listToWrite){
            dao.insertPost(post)
        }

        val dbPosts = ArrayList(dao.getAllPosts())
        for((index,post) in dbPosts.withIndex()){

            MatcherAssert.assertThat(post.id, CoreMatchers.equalTo(listToWrite[index].id))
            MatcherAssert.assertThat(post.title, CoreMatchers.equalTo(listToWrite[index].title))
            MatcherAssert.assertThat(post.body, CoreMatchers.equalTo(listToWrite[index].body))
            MatcherAssert.assertThat(post.isFav, CoreMatchers.equalTo(listToWrite[index].isFav))
        }
    }

}