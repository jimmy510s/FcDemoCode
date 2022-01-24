package com.example.fieldcodedemoapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fieldcodedemoapp.data.db.DbHelper
import com.example.fieldcodedemoapp.data.model.Post
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {

    @Test
    fun writePostsAndRetrieveThem() {

        val listToWrite = ArrayList<Post>()
        val post1 = Post(1L,"TestTitle1","TestBody1",false)
        val post2 = Post(1L,"TestTitle2","TestBody2",false)
        val post3 = Post(1L,"TestTitle3","TestBody3",false)
        listToWrite.add(post1)
        listToWrite.add(post2)
        listToWrite.add(post3)

        val dbHelper = DbHelper()
        for(post in listToWrite){
            dbHelper.savePostToDb(post)
        }

        for((index,post) in dbHelper.getPostsFromDb().withIndex()){

            assertThat(post.id, equalTo(listToWrite[index].id))
            assertThat(post.title, equalTo(listToWrite[index].title))
            assertThat(post.body, equalTo(listToWrite[index].body))
            assertThat(post.isFav, equalTo(listToWrite[index].isFav))
        }
    }
}