package com.xoxoer.postjsonplaceholder.ui.post

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xoxoer.postjsonplaceholder.R
import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.ui.post.adapter.PostsAdapter
import com.xoxoer.postjsonplaceholder.util.common.createLoading
import com.xoxoer.postjsonplaceholder.util.common.onSearchPressed
import com.xoxoer.postjsonplaceholder.util.common.onTextChange
import com.xoxoer.postjsonplaceholder.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : DaggerAppCompatActivity() {

    private lateinit var postsAdapter: PostsAdapter
    private lateinit var postsViewModel: PostsViewModel
    private lateinit var loadingDialog: Dialog

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private fun initUI() {
        loadingDialog = createLoading()
        editTextSearchPost.onTextChange { query ->
            postsViewModel.apply {
                searchPostQuery.set(query)
                if (query.isEmpty()) retrievePosts()
            }
        }
        editTextSearchPost.onSearchPressed {
            postsViewModel.retrievePostsWithQuery()
        }
    }

    private fun bindUI(posts: List<PostsItem>) {
        postsAdapter.setPosts(posts)
    }

    private fun initPostsAdapter() {
        postsAdapter = PostsAdapter()
        recyclerViewPosts.apply {
            setHasFixedSize(true)
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(this@PostActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize post view model
        postsViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(PostsViewModel::class.java)

        setContentView(R.layout.activity_post)

        // initialize ui
        initUI()
        initPostsAdapter()

        // call get network call retrofit
        postsViewModel.retrievePosts()

        // loading observer
        postsViewModel.isLoading.observe(this, Observer { loading ->
            if (loading) loadingDialog.show()
            else loadingDialog.dismiss()
        })

        // post from network calls or room
        postsViewModel.postsSuccess.observe(this@PostActivity, Observer { posts ->
            if (posts == null) return@Observer
            else bindUI(posts)
        })
    }
}