package com.cookandroid.instagram_android_moon.src.post.posting

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.post.model.PostPostRequest
import com.cookandroid.instagram_android_moon.src.post.model.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostingService(val postingFragmentInterface: PostingFragmentInterface) {

    fun tryPostPost(params: PostPostRequest){
        val postingRetrofitInterface = ApplicationClass.sRetrofit.create(PostingRetrofitInterface::class.java)
        postingRetrofitInterface.postPost(params).enqueue(object:Callback<PostResponse>{
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ) {
                postingFragmentInterface.onPostPostSuccess(response.body() as PostResponse)
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                postingFragmentInterface.onPostPostFailure(t.message ?: "통신 오류")
            }
        })
    }

}