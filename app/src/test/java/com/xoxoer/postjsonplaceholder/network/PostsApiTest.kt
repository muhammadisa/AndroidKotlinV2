package com.xoxoer.postjsonplaceholder.network

import com.xoxoer.postjsonplaceholder.RxTrampolineSchedulerRule
import com.xoxoer.postjsonplaceholder.model.PostsItem
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import javax.inject.Inject

class AppRepositoryInjectTest {

    @Inject
    lateinit var postsApi: PostsApi

    @Before
    fun setup(){
        val component = DaggerApp
    }
}

//class PostsApiTest : ApiAbstract<PostsApi>() {

//    private lateinit var service: PostsApi
//
//    @Rule
//    @JvmField
//    var testSchedulerRule = RxTrampolineSchedulerRule()
//
//    @Before
//    fun initService() {
//        service = createService(PostsApi::class.java)
//    }
//
//    @Throws(IOException::class)
//    @Test
//    fun retrievePosts() = runBlocking {
//        enqueueResponse("/Posts.json")
//        val response = service.retrievePosts()
//            .subscribeOn(Schedulers.trampoline())
//            .observeOn(Schedulers.trampoline())
//            .blockingGet()
//        val responseBody = requireNotNull((response as List<PostsItem>))
//        mockWebServer.takeRequest()
//        assertThat(responseBody.size, `is`(3))
//    }

//}