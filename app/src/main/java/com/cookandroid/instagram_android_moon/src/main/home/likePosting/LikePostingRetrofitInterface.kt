package com.cookandroid.instagram_android_moon.src.main.home.likePosting

import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface LikePostingRetrofitInterface {
    @POST("/app/posts/likes/{post-id}")
    fun postLikePosting(@Path("post-id") post_id: Int): Call<LikePostingResponse>

    @PATCH("app/posts/likes/{like-id}/{status}")
    fun patchUnLikePosting(
        @Path("like-id") like_id: Int,
        @Path("status") status: Int,
    ): Call<LikePostingResponse>
}