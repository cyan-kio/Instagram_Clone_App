package com.cookandroid.instagram_android_moon.src.comment.LikeComment

import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeCommentRetrofitInterface {
    @POST("app/posts/comments/like-status/{comment-id}")
    fun postLikeComment(@Path("comment-id") comment_id: Int): Call<LikeCommentResponse>

    @PATCH("app/posts/comments/like-status/{like-id}/{status}")
    fun patchUnLikeComment(
        @Path("like-id") like_id: Int,
        @Path("status") status: Int,
    ): Call<LikeCommentResponse>
}