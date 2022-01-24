package com.example.fieldcodedemoapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.fieldcodedemoapp.data.db.AppDatabase
import com.example.fieldcodedemoapp.data.db.DbHelper
import com.example.fieldcodedemoapp.data.model.Post
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SimpleEntityReadWriteTest {


    @Test
    fun writePostsAndRetrieveThem() {

        val appContext = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.databaseBuilder(appContext, AppDatabase::class.java,"fieldcodedb").fallbackToDestructiveMigration()
            .build()

        val listToWrite = ArrayList<Post>()
        val post1 = Post(1L,"TestTitle1","TestBody1",false)
        val post2 = Post(1L,"TestTitle2","TestBody2",false)
        val post3 = Post(1L,"TestTitle3","TestBody3",false)
        listToWrite.add(post1)
        listToWrite.add(post2)
        listToWrite.add(post3)


        val dbHelper = TestDbHelper()
        for(post in listToWrite){
            dbHelper.savePostToDb(post,db.postDao())
        }

        for((index,post) in dbHelper.getPostsFromDb(db.postDao()).withIndex()){

            assertThat(post.id, equalTo(listToWrite[index].id))
            assertThat(post.title, equalTo(listToWrite[index].title))
            assertThat(post.body, equalTo(listToWrite[index].body))
            assertThat(post.isFav, equalTo(listToWrite[index].isFav))
        }
    }
}