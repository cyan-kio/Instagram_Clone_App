package com.cookandroid.instagram_android_moon.src.comment.model

import com.google.gson.annotations.SerializedName

data class PostWriteCommentRequest(
    @SerializedName("postId") val postId: Int,
    @SerializedName("groupId") val groupId: Int = 0,
    @SerializedName("comment") val comment: String
)
