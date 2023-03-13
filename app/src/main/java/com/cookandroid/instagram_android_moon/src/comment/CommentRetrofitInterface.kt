package com.cookandroid.instagram_android_moon.src.comment

import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentRetrofitInterface {
    @GET("app/posts/comments/{post-id}")
    fun getComments(@Path("post-id") user_id: Int): Call<CommentsResponse>
}