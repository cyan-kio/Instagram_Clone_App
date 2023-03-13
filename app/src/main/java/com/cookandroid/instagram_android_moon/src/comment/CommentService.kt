package com.cookandroid.instagram_android_moon.src.comment

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentService(val commentActivityInterface: CommentActivityInterface) {

    fun tryGetComments(postId: Int) {
        val commentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        commentRetrofitInterface.getComments(postId).enqueue(object: Callback<CommentsResponse>{
            override fun onResponse(
                call: Call<CommentsResponse>,
                response: Response<CommentsResponse>
            ) {
                commentActivityInterface.onGetCommentsSuccess(response.body() as CommentsResponse)
            }

            override fun onFailure(call: Call<CommentsResponse>, t: Throwable) {
                commentActivityInterface.onGetCommentsFailure(t.message ?: "통신 오류")
            }
        })
    }
}