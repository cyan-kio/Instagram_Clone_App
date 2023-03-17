package com.cookandroid.instagram_android_moon.src.post.model

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    var content : String = ""
    var place : String? = null
    var likeShowStatus : Int = 0
    var commentShowStatus : Int = 0
    var photos: MutableList<PhotoPost> = mutableListOf<PhotoPost>()
    var tagphotosord : MutableList<String> = mutableListOf<String>()
}