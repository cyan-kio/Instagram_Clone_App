package com.cookandroid.instagram_android_moon.src.post

import android.os.Bundle
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityPostBinding
import com.cookandroid.instagram_android_moon.src.post.posting.PostingFragment
import com.cookandroid.instagram_android_moon.src.post.select.SelectPhotoFragment

class PostActivity : BaseActivity<ActivityPostBinding>(ActivityPostBinding::inflate) {

    lateinit var selectPhotoFragment: SelectPhotoFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectPhotoFragment = SelectPhotoFragment()

        changeFragment(R.id.container_post, selectPhotoFragment)
    }
}