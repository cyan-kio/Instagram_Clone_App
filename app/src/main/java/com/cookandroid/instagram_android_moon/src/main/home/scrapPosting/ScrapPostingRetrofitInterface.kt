package com.cookandroid.instagram_android_moon.src.main.home.scrapPosting

import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.model.ScrapPostingResponse
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ScrapPostingRetrofitInterface {
    @POST("app/posts/scraped/{post-id}")
    fun postScrapPosting(@Path("post-id") post_id: Int): Call<ScrapPostingResponse>

    @PATCH("app/posts/scraped/{scrap-id}/{status}")
    fun patchEditScrapPosting(
        @Path("scrap-id") scrap_id: Int,
        @Path("status") status: Boolean,
    ): Call<ScrapPostingResponse>
}