package com.cookandroid.instagram_android_moon.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivitySplashBinding
import com.cookandroid.instagram_android_moon.src.signin.SignInActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, 1500)
    }
}