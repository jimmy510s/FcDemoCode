package com.example.fieldcodedemoapp.screen.postlist

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.screen.base.BaseToolbarScreen
import com.example.fieldcodedemoapp.screen.postDetails.PostDetailsScreen

class PostListScreen : BaseToolbarScreen(), PostListContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var presenter: PostListPresenter
    private lateinit var adapter: PostListAdapter

    override fun getLayoutId(): Int {
        return R.layout.post_list_screen
    }

    override fun hasBackButton(): Boolean {
        return false
    }

    override fun elementDeclaration() {
        recyclerView = findViewById(R.id.recyclerview)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PostListPresenter(this)

        setTitle(R.string.app_name)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        adapter = PostListAdapter(presenter.dataList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {post ->
            presenter.onItemClick(post)
        }

        adapter.onFavClick ={ pos ->
            presenter.onFavClick(pos)
        }

        refreshLayout.setOnRefreshListener {
            presenter.getPosts()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getPosts()
    }

    override fun showLoading() {
        refreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        refreshLayout.isRefreshing = false
    }

    override fun goToDetailsScreen(post: Post) {
        val intent = Intent(this,PostDetailsScreen::class.java)
        intent.putExtra(PostDetailsScreen.EXTRA_POST,post)
        startActivity(intent)
    }

    override fun updateFav(pos: Int) {
        presenter.dataList[pos].isFav = !presenter.dataList[pos].isFav
        presenter.updatePost(presenter.dataList[pos])
        adapter.notifyDataSetChanged()
    }

    override fun getPosts() {
        adapter.notifyDataSetChanged()
    }

    override fun handleServerError(msg: String?) {

        var errorMsg = getString(R.string.general_error)
        if(!msg.isNullOrBlank())
            errorMsg = msg

        showNotificationDialog(errorMsg, msg, null, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}