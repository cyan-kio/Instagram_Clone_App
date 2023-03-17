package com.cookandroid.instagram_android_moon.src.signup.model

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var signup_method : Int = -1
    var verify_method : String = ""
    var name : String = ""
    var password : String = ""
    var signin_state: Boolean = false
    var birth_date : String = ""
    var nickname : String = ""
    var profile_image_url : String? = null
}