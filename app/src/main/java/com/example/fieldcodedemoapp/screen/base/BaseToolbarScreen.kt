package com.example.fieldcodedemoapp.screen.base

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.fieldcodedemoapp.R

abstract class BaseToolbarScreen : BaseScreen() {

    protected abstract fun hasBackButton(): Boolean
    protected abstract fun elementDeclaration()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setUpToolbar()
        elementDeclaration()
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