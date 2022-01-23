package com.example.fieldcodedemoapp.screen.postDetails

import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.screen.base.BaseToolbarScreen


class PostDetailsScreen: BaseToolbarScreen()
{
    override fun hasBackButton(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.post_details_screen
    }
}