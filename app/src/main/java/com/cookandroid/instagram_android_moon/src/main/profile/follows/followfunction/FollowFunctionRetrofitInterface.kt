package com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction

import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FollowFunctionRetrofitInterface {

    @POST("app/follows/{user-id}")
    fun follow(
        @Path("user-id") user_id: Int,
        @Query("follow-user-id") follow_user_id: Int
    ): Call<FollowResponse>

    @PATCH("app/follows/{user-id}")
    fun unFollow(
        @Path("user-id") user_id: Int,
        @Query("follow-user-id") follow_user_id: Int
    ): Call<FollowResponse>
}