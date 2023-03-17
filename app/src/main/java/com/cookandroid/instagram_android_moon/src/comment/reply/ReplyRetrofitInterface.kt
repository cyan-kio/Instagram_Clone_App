package com.cookandroid.instagram_android_moon.src.comment.reply

import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReplyRetrofitInterface {
    @GET("app/posts/comments/big-comment")
    fun getReplies(@Query("parent-id") parent_id: Int): Call<CommentsResponse>
}