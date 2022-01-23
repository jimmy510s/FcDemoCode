package com.example.fieldcodedemoapp.screen.postDetails

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.screen.base.BaseToolbarScreen


class PostDetailsScreen: BaseToolbarScreen(), PostDetailsContract.View {

    private lateinit var edtTitle: EditText
    private lateinit var edtBody: EditText
    private lateinit var btnAction: Button

    private lateinit var presenter: PostDetailsPresenter

    companion object {
        const val EXTRA_POST = "EXTRA_POST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PostDetailsPresenter(this)
        presenter.setData(intent.extras?.get(EXTRA_POST) as Post)

        btnAction.setOnClickListener(clickListener)
    }

    override fun hasBackButton(): Boolean {
        return true
    }

    override fun elementDeclaration() {
        edtTitle = findViewById(R.id.post_title_edt)
        edtBody = findViewById(R.id.post_body_edt)
        btnAction = findViewById(R.id.ok_action)
    }

    private val clickListener = View.OnClickListener {
        presenter.onOkClick(edtBody.text.toString())
    }

    override fun getLayoutId(): Int {
        return R.layout.post_details_screen
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun setData(post: Post) {
        edtTitle.setText(post.title)
        edtBody.setText(post.body)
    }

    override fun showBodyError() {
        edtBody.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red))
    }

    override fun handleServerError(msg: String?) {

        var errorMsg = getString(R.string.general_error)
        if(!msg.isNullOrBlank())
            errorMsg = msg

        showNotificationDialog(errorMsg, msg, null, false)
    }

    override fun postUpdatedSuccessFully() {
        Toast.makeText(this,R.string.update_post_success,Toast.LENGTH_SHORT).show()
    }

    override fun goBack() {
        onBackPressed()
    }
}