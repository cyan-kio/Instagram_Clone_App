package com.cookandroid.instagram_android_moon.src.signin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.ApplicationClass.Companion.LOGIN_USER_ID
import com.cookandroid.instagram_android_moon.config.ApplicationClass.Companion.sSharedPreferences
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivitySignInBinding
import com.cookandroid.instagram_android_moon.src.main.MainActivity
import com.cookandroid.instagram_android_moon.src.signin.model.PostSignInRequest
import com.cookandroid.instagram_android_moon.src.signin.model.SignInResponse
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(ActivitySignInBinding::inflate), SignInActivityInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sign In
        binding.btnSignInSignIn.setOnClickListener {
            val id = binding.edtSignInUserId.text.toString()
            val password = binding.edtSignInPassword.text.toString()
            val postRequest = PostSignInRequest(id = id, password = password)
            SignInService(this).tryPostSignIn(postRequest)
        }

        // Sign Up
        binding.btnSignInSignUp.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }

    override fun onPostSignInSuccess(response: SignInResponse) {
        val editor: SharedPreferences.Editor = sSharedPreferences.edit()
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
        editor.putString(LOGIN_USER_ID, response.result.user_id.toString())
        editor.commit()
        response.message?.let { showCustomToast(it) }
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
    }

    override fun onPostSignInFailure(message: String) {
        showCustomToast("오류 : $message")
            Log.d("SignInError", "$message")
    }
}