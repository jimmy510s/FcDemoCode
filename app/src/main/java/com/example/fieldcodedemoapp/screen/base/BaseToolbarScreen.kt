package com.example.fieldcodedemoapp.screen.base

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.screen.base.BaseScreen

abstract class BaseToolbarScreen : BaseScreen() {

    protected abstract fun hasBackButton(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setUpToolbar()
    }

    private fun setUpToolbar() {

        if (findViewById<Toolbar>(R.id.toolbar) != null) {

            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            if(hasBackButton())
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}