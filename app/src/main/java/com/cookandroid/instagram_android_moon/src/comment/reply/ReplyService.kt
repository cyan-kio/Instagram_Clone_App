package com.cookandroid.instagram_android_moon.src.comment.reply

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyService(val replyAdapterInterface: ReplyAdapterInterface) {

    fun tryGetReplies(postId: Int) {
        val replyRetrofitInterface = ApplicationClass.sRetrofit.create(ReplyRetrofitInterface::class.java)
        replyRetrofitInterface.getReplies(postId).enqueue(object: Callback<CommentsResponse>{
            override fun onResponse(
                call: Call<CommentsResponse>,
                response: Response<CommentsResponse>
            ) {
                replyAdapterInterface.onGetRepliesSuccess(response.body() as CommentsResponse)
            }

            override fun onFailure(call: Call<CommentsResponse>, t: Throwable) {
                replyAdapterInterface.onGetRepliesFailure(t.message ?: "통신 오류")
            }
        })
    }
}