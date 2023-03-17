package com.cookandroid.instagram_android_moon.src.post.model

import com.google.gson.annotations.SerializedName

data class PostPostRequest(
    @SerializedName("content") val content: String,
    @SerializedName("place") val place: String,
    @SerializedName("likeShowStatus") val likeShowStatus: Int,
    @SerializedName("commentShowStatus") val commentShowStatus: Int,
    @SerializedName("photos") val photos: MutableList<PhotoPost>,
    @SerializedName("tagphotosord") val tagphotosord: MutableList<String>
)
