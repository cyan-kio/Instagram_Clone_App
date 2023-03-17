package com.cookandroid.instagram_android_moon.src.discover.contact

import android.content.Intent
import android.os.Bundle
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityDiscoverContactBinding
import com.cookandroid.instagram_android_moon.src.main.MainActivity

class DiscoverContactActivity : BaseActivity<ActivityDiscoverContactBinding>(ActivityDiscoverContactBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnDiscoverDiscoverContactSkip.setOnClickListener {
            startActivity(Intent(this@DiscoverContactActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            })

            finish()
        }
    }
}