package com.cookandroid.instagram_android_moon.src.main.home.model

import com.google.gson.annotations.SerializedName

data class ResultHomeFeeds (
    @SerializedName("postId") val postId: Int,
    @SerializedName("content") val content: String,
    @SerializedName("place") val place: String?,
    @SerializedName("likeShowStatus") val likeShowStatus: Int,
    @SerializedName("commentShowStatus") val commentShowStatus: Int,
    @SerializedName("photos") val photos: MutableList<PhotoHomeFeeds>,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("profileName") val profileName: String,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("scrapOn") val scrapOn: ScrapAndLikeOn,
    @SerializedName("tagWord") val tagWord: MutableList<String>,
    @SerializedName("likeOn") val likeOn: ScrapAndLikeOn,
    @SerializedName("userStoryOn") val userStoryOn: Int,
    @SerializedName("userFollowOn") val userFollowOn: Int
)

data class ScrapAndLikeOn (
    @SerializedName("id") val id: Int,
    @SerializedName("on") val on: Int
)