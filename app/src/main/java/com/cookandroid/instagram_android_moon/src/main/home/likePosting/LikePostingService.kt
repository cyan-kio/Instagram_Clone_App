package com.cookandroid.instagram_android_moon.src.main.home.likePosting

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.home.HomeFragmentInterface
import com.cookandroid.instagram_android_moon.src.main.home.HomeRetrofitInterface
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikePostingService(val likePostingInterface: LikePostingInterface) {
    private val likePostingRetrofitInterface = ApplicationClass.sRetrofit.create(LikePostingRetrofitInterface::class.java)

    fun tryPostLikePosting(post_id: Int) {
        likePostingRetrofitInterface.postLikePosting(post_id).enqueue(object: Callback<LikePostingResponse>{
            override fun onResponse(
                call: Call<LikePostingResponse>,
                response: Response<LikePostingResponse>
            ) {
                likePostingInterface.onPostLikePostingSuccess(response.body() as LikePostingResponse)
            }

            override fun onFailure(call: Call<LikePostingResponse>, t: Throwable) {
                likePostingInterface.onPostLikePostingFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchUnLikePosting(like_id: Int, status: Int) {
        likePostingRetrofitInterface.patchUnLikePosting(like_id, status).enqueue(object: Callback<LikePostingResponse>{
            override fun onResponse(
                call: Call<LikePostingResponse>,
                response: Response<LikePostingResponse>
            ) {
                likePostingInterface.onPatchUnLikePostingSuccess(response.body() as LikePostingResponse)
            }

            override fun onFailure(call: Call<LikePostingResponse>, t: Throwable) {
                likePostingInterface.onPatchUnLikePostingFailure(t.message ?: "통신 오류")
            }
        })
    }
}