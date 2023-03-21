package com.cookandroid.instagram_android_moon.src.main.home.scrapPosting

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.model.ScrapPostingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScrapPostingService(val scrapPostingInterface: ScrapPostingInterface) {
    private val scrapPostingRetrofitInterface = ApplicationClass.sRetrofit.create(ScrapPostingRetrofitInterface::class.java)

    fun tryPostScrapPosting(post_id: Int) {
        scrapPostingRetrofitInterface.postScrapPosting(post_id).enqueue(object: Callback<ScrapPostingResponse>{
            override fun onResponse(
                call: Call<ScrapPostingResponse>,
                response: Response<ScrapPostingResponse>
            ) {
                scrapPostingInterface.onPostScrapPostingSuccess(response.body() as ScrapPostingResponse)
            }

            override fun onFailure(call: Call<ScrapPostingResponse>, t: Throwable) {
                scrapPostingInterface.onPostScrapPostingFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchEditScrapPosting(scrap_id: Int, status: Boolean) {
        scrapPostingRetrofitInterface.patchEditScrapPosting(scrap_id, status).enqueue(object: Callback<ScrapPostingResponse>{
            override fun onResponse(
                call: Call<ScrapPostingResponse>,
                response: Response<ScrapPostingResponse>
            ) {
                scrapPostingInterface.onPatchEditScrapPostingSuccess(response.body() as ScrapPostingResponse)
            }

            override fun onFailure(call: Call<ScrapPostingResponse>, t: Throwable) {
                scrapPostingInterface.onPatchEditScrapPostingFailure(t.message ?: "통신 오류")
            }
        })
    }
}