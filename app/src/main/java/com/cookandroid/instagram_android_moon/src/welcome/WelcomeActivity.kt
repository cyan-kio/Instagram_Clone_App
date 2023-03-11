package com.cookandroid.instagram_android_moon.src.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityWelcomeBinding
import com.cookandroid.instagram_android_moon.src.discover.facebook.DiscoverFacebookActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>(ActivityWelcomeBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, DiscoverFacebookActivity::class.java))
            finish()
        }, 1500)
    }
}