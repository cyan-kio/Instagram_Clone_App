package com.cookandroid.instagram_android_moon.src.story.model

import com.google.gson.annotations.SerializedName

data class ResultUserStories(
    @SerializedName("user_story_id") val user_story_id: Int,
    @SerializedName("story_viewer_count") val story_viewer_count: Int?,
    @SerializedName("story_viewer_profile_image_urls") val story_viewer_profile_image_urls: MutableList<String>,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile_image_url") val profile_image_url: String,
    @SerializedName("story_url") val story_url: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String
)