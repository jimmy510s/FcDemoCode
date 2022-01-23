package com.example.fieldcodedemoapp.screen.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (hasBackButton()){
            onBackPressed()
            return true
        }else{
            return super.onOptionsItemSelected(item)
        }
    }
}