package com.cookandroid.instagram_android_moon.src.main.profile.model

import com.google.gson.annotations.SerializedName

data class ResultProfileFeeds (
    @SerializedName("postId") val postId: Int,
    @SerializedName("content") val content: String,
    @SerializedName("place") val place: String?,
    @SerializedName("likeShowStatus") val likeShowStatus: Int,
    @SerializedName("commentShowStatus") val commentShowStatus: Int,
    @SerializedName("photos") val photos: MutableList<PhotoProfileFeeds>,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("profileName") val profileName: String,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("scrapOn") val scrapOn: ScrapAndLikeOn,
    @SerializedName("tagWord") val tagWord: MutableList<String>,
    @SerializedName("likeOn") val likeOn: ScrapAndLikeOn
)

data class ScrapAndLikeOn (
    @SerializedName("id") val id: Int,
    @SerializedName("on") val on: Int
)