package com.cookandroid.instagram_android_moon.src.signin

import android.os.Bundle
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivitySignInBinding

class SignInActivity : BaseActivity<ActivitySignInBinding>(ActivitySignInBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }
}