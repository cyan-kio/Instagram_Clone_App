package com.cookandroid.instagram_android_moon.src.post.model

import com.google.gson.annotations.SerializedName

data class PhotoPost(
    @SerializedName("photoUrl") var photoUrl: String,
    @SerializedName("userTagId") val userTagId: MutableList<String>
)
