package com.xoxoer.postjsonplaceholder

import com.xoxoer.postjsonplaceholder.model.PostsItem

object MockUtil {
    fun mockPost() = PostsItem(
        1,
        "This is body of post item",
        "Title of this post item",
        2
    )
}