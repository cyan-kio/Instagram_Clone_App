package com.cookandroid.instagram_android_moon.src.comment

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.src.comment.model.PostWriteCommentRequest
import com.cookandroid.instagram_android_moon.src.comment.model.WriteCommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentService(val commentActivityInterface: CommentActivityInterface) {
    private val commentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
    fun tryGetComments(postId: Int) {
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
    fun tryPostWriteComment(params: PostWriteCommentRequest) {
        commentRetrofitInterface.postWriteComment(params).enqueue(object: Callback<WriteCommentResponse>{
            override fun onResponse(
                call: Call<WriteCommentResponse>,
                response: Response<WriteCommentResponse>
            ) {
                commentActivityInterface.onPostWriteCommentSuccess(response.body() as WriteCommentResponse)
            }

            override fun onFailure(call: Call<WriteCommentResponse>, t: Throwable) {
                commentActivityInterface.onPostWriteCommentFailure(t.message ?: "통신 오류")
            }
        })
    }

}