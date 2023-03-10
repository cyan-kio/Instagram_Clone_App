package com.cookandroid.instagram_android_moon.src.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivitySignInBinding
import com.cookandroid.instagram_android_moon.src.main.MainActivity
import com.cookandroid.instagram_android_moon.src.signin.model.PostSignInRequest
import com.cookandroid.instagram_android_moon.src.signin.model.SignInResponse

class SignInActivity : BaseActivity<ActivitySignInBinding>(ActivitySignInBinding::inflate), SignInActivityInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSignInSignIn.setOnClickListener {
            val id = binding.edtSignInUserId.text.toString()
            val password = binding.edtSignInPassword.text.toString()
            val postRequest = PostSignInRequest(id = id, password = password)
            SignInService(this).tryPostSignIn(postRequest)
        }
    }

    override fun onPostSignInSuccess(response: SignInResponse) {
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        response.message.let { showCustomToast(it) }
    }

    override fun onPostSignInFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("SignInError", "$message")
    }
}