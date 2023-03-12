package com.cookandroid.instagram_android_moon.src.main.profile.model

import com.google.gson.annotations.SerializedName

data class PhotoProfileFeeds(
    @SerializedName("photoUrl") val photoUrl: String,
    @SerializedName("userTagId") val userTagId: MutableList<String>
)
