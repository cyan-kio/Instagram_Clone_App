package com.cookandroid.instagram_android_moon.src.main.recommended.model

import com.google.gson.annotations.SerializedName

data class ResultRecommended(
    @SerializedName("postId") val postId: Int,
    @SerializedName("firstPhotoUrl") val firstPhotoUrl: String
)
