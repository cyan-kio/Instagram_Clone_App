package com.cookandroid.instagram_android_moon.src.main.home.likePosting

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikePostingService(val likePostingInterface: LikePostingInterface) {
    private val likePostingRetrofitInterface = ApplicationClass.sRetrofit.create(LikePostingRetrofitInterface::class.java)

    fun tryPostLikePosting(post_id: Int) {
        likePostingRetrofitInterface.postLikePosting(post_id).enqueue(object: Callback<LikeCommentResponse>{
            override fun onResponse(
                call: Call<LikeCommentResponse>,
                response: Response<LikeCommentResponse>
            ) {
                likePostingInterface.onPostLikePostingSuccess(response.body() as LikeCommentResponse)
            }

            override fun onFailure(call: Call<LikeCommentResponse>, t: Throwable) {
                likePostingInterface.onPostLikePostingFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchUnLikePosting(like_id: Int, status: Int) {
        likePostingRetrofitInterface.patchUnLikePosting(like_id, status).enqueue(object: Callback<LikeCommentResponse>{
            override fun onResponse(
                call: Call<LikeCommentResponse>,
                response: Response<LikeCommentResponse>
            ) {
                likePostingInterface.onPatchUnLikePostingSuccess(response.body() as LikeCommentResponse)
            }

            override fun onFailure(call: Call<LikeCommentResponse>, t: Throwable) {
                likePostingInterface.onPatchUnLikePostingFailure(t.message ?: "통신 오류")
            }
        })
    }
}