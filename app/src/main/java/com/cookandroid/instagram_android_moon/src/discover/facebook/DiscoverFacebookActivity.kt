package com.cookandroid.instagram_android_moon.src.discover.facebook


import android.content.Intent
import android.os.Bundle
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityDiscoverFacebookBinding
import com.cookandroid.instagram_android_moon.src.discover.contact.DiscoverContactActivity

class DiscoverFacebookActivity : BaseActivity<ActivityDiscoverFacebookBinding>(
    ActivityDiscoverFacebookBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnDiscoverFacebookFriendsSkip.setOnClickListener {
            startActivity(Intent(this@DiscoverFacebookActivity, DiscoverContactActivity::class.java))
        }
    }
}