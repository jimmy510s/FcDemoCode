package com.example.fieldcodedemoapp.screen.postlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fieldcodedemoapp.R
import com.example.fieldcodedemoapp.data.model.Post

class PostListAdapter(private val dataSet: List<Post>, private val context: Context) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    var onItemClick: ((Post) -> Unit)? = null
    var onFavClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.post_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tvPostId.text = dataSet[position].id.toString()
        viewHolder.tvPostBody.text = dataSet[position].body
        viewHolder.tvPostTitle.text = dataSet[position].title

        if(dataSet[position].isFav != null && dataSet[position].isFav!!)
            viewHolder.ivFav.setImageResource(R.drawable.ic_fav)
        else
            viewHolder.ivFav.setImageResource(R.drawable.ic_not_fav)

        viewHolder.ivFav.tag = position
    }


    override fun getItemCount() = dataSet.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvPostId: TextView = view.findViewById(R.id.post_id_tv)
        val tvPostTitle: TextView = view.findViewById(R.id.post_title_tv)
        val tvPostBody: TextView = view.findViewById(R.id.post_body_tv)
        val ivFav: ImageView = view.findViewById(R.id.post_fav_iv)

        init {
            view.setOnClickListener()
            {
                onItemClick?.invoke(dataSet[absoluteAdapterPosition])
            }

            ivFav.setOnClickListener {
                onFavClick?.invoke(it.tag as Int)
            }
        }
    }
}