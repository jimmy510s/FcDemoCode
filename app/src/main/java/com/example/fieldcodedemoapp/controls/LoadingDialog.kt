package com.example.fieldcodedemoapp.controls

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.fieldcodedemoapp.R


class LoadingDialog(context: Context?) : Dialog(context!!) {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.alert_dialog)
        window?.setBackgroundDrawableResource(R.color.transparent)
    }
}