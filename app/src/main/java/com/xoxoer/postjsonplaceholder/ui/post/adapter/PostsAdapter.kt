package com.xoxoer.postjsonplaceholder.ui.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.postjsonplaceholder.R
import com.xoxoer.postjsonplaceholder.model.PostsItem
import kotlinx.android.synthetic.main.card_view_post.view.*

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val posts: MutableList<PostsItem> = mutableListOf()

    internal fun setPosts(posts: List<PostsItem>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPostTitle: TextView = itemView.textViewPostTitle
        val textViewPostBody: TextView = itemView.textViewPostBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_post, parent, false)
        return PostsViewHolder(itemView)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = posts[position]
        with(holder) {
            textViewPostTitle.text = post.title
            textViewPostBody.text = post.body
        }
    }

}