package com.cookandroid.instagram_android_moon.src.comment

import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.src.comment.model.PostWriteCommentRequest
import com.cookandroid.instagram_android_moon.src.comment.model.WriteCommentResponse
import com.cookandroid.instagram_android_moon.src.signin.model.PostSignInRequest
import retrofit2.Call
import retrofit2.http.*

interface CommentRetrofitInterface {
    @GET("app/posts/comments/{post-id}")
    fun getComments(@Path("post-id") post_id: Int): Call<CommentsResponse>

    @POST("app/posts/comments")
    fun postWriteComment(@Body params: PostWriteCommentRequest): Call<WriteCommentResponse>
}