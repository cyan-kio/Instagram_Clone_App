package com.cookandroid.instagram_android_moon.src.main.recommended.view

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.recommended.view.model.ViewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewService(val viewFragmentInterface: ViewFragmentInterface) {
    private val viewRetrofitInterface = ApplicationClass.sRetrofit.create(ViewRetrofitInterface::class.java)
    fun tryGetView(postId: Int){
        viewRetrofitInterface.getView(postId).enqueue(object:Callback<ViewResponse>{
            override fun onResponse(
                call: Call<ViewResponse>,
                response: Response<ViewResponse>
            ) {
                viewFragmentInterface.onGetViewSuccess(response.body() as ViewResponse)
            }

            override fun onFailure(call: Call<ViewResponse>, t: Throwable) {
                viewFragmentInterface.onGetViewFailure(t.message ?: "통신 오류")
            }
        })
    }

}