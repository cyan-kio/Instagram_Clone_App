package com.cookandroid.instagram_android_moon.src.post.posting

import com.cookandroid.instagram_android_moon.src.comment.model.PostWriteCommentRequest
import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.post.model.PostPostRequest
import com.cookandroid.instagram_android_moon.src.post.model.PostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostingRetrofitInterface {
    @POST("app/posts")
    fun postPost(@Body params: PostPostRequest): Call<PostResponse>
}