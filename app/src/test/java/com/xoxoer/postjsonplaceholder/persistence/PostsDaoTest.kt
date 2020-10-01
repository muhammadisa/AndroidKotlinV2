package com.xoxoer.postjsonplaceholder.persistence

import com.xoxoer.postjsonplaceholder.MockUtil
import com.xoxoer.postjsonplaceholder.model.PostsItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PostsDaoTest : LocalDatabase() {

    private lateinit var postsDao: PostsDao
    private lateinit var listPosts: List<PostsItem>

    @Before
    fun init() {
        postsDao = db.postsDao()
        listPosts = listOf(
            MockUtil.mockPost(1, 2),
            MockUtil.mockPost(3, 4),
            MockUtil.mockPost(5, 6),
            MockUtil.mockPost(7, 8)
        )
        listPosts.forEach { post ->
            postsDao.insertPost(post)
        }
    }

    @Test
    fun insertAndLoadAllPostsFromRoomDatabase() {
        val loadedPosts = postsDao.getPosts().blockingGet()
        loadedPosts.forEach { loadedPost ->
            assertThat(loadedPost.id, `is`(loadedPost.id))
            assertThat(loadedPost.userId, `is`(loadedPost.userId))
            assertThat(loadedPost.title, `is`(loadedPost.title))
            assertThat(loadedPost.body, `is`(loadedPost.body))
        }
    }

    @Test
    fun insertAndLoadPostFromRoomDatabase() {
        val loadedPost = postsDao.getPost(7).blockingGet()
        assertThat(loadedPost.id, `is`(7))
        assertThat(loadedPost.userId, `is`(8))
        assertThat(loadedPost.title, `is`(MockUtil.TITLE))
        assertThat(loadedPost.body, `is`(MockUtil.BODY))
    }

}