package com.cookandroid.instagram_android_moon.src.main.home.scrapPosting

import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.model.ScrapPostingResponse

interface ScrapPostingInterface {

    fun onPostScrapPostingSuccess(response: ScrapPostingResponse)

    fun onPostScrapPostingFailure(message: String)

    fun onPatchEditScrapPostingSuccess(response: ScrapPostingResponse)

    fun onPatchEditScrapPostingFailure(message: String)
}