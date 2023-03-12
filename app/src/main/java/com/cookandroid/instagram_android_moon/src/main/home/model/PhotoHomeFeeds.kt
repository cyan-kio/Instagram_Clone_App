package com.cookandroid.instagram_android_moon.src.main.home.model

import com.google.gson.annotations.SerializedName

data class PhotoHomeFeeds(
    @SerializedName("photoUrl") val photoUrl: String,
    @SerializedName("userTagId") val userTagId: MutableList<String>
)
