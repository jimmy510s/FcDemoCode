package com.example.fieldcodedemoapp.screen.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.controls.LoadingDialog

abstract class BaseScreen : AppCompatActivity() {

    protected abstract fun getLayoutId(): Int

    private var mLdDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
    }

    fun showLoadingDialog() {

        if (mLdDialog == null)
            mLdDialog = LoadingDialog(this)

        if (!isFinishing)
            mLdDialog?.show()
    }

    fun hideLoadingDialog() {

        mLdDialog?.dismiss()
    }

    fun handleServerError(errorMessage: String?) {

        showNotificationDialog(R.string.app_name, getString(R.string.general_error), null, false)
    }

    fun showNotificationDialog(title: Int, message: String?, clickListener: DialogInterface.OnClickListener?, cancelable: Boolean) {

        if (!isFinishing) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setNeutralButton(resources.getString(R.string.ok), clickListener)
            val alert = builder.create()
            if (!isFinishing) {
                alert.show()
            }
        }
    }
}