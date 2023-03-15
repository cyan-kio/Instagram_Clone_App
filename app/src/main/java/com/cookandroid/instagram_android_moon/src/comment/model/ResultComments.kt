package com.cookandroid.instagram_android_moon.src.comment.model

import com.cookandroid.instagram_android_moon.src.main.home.model.PhotoHomeFeeds
import com.google.gson.annotations.SerializedName

data class ResultComments(
    @SerializedName("commentId") val commentId: Int,
    @SerializedName("postId") val postId: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("profileName") val profileName: String,
    @SerializedName("groupId") val groupId: Int,
    @SerializedName("comment") val comment: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("bigCommentCount") val bigCommentCount: Int,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("likeOn") val likeOn: ScrapAndLikeOn
)

data class ScrapAndLikeOn (
    @SerializedName("id") val id: Int,
    @SerializedName("on") val on: Int
)