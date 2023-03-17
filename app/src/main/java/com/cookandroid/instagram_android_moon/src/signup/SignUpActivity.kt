package com.cookandroid.instagram_android_moon.src.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.ApplicationClass.Companion.LOGIN_USER_ID
import com.cookandroid.instagram_android_moon.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivitySignUpBinding
import com.cookandroid.instagram_android_moon.src.signup.birthdate.GetBirthDateFragment
import com.cookandroid.instagram_android_moon.src.signup.certification.GetCertificationCodeFragment
import com.cookandroid.instagram_android_moon.src.signup.email.GetEmailAddressFragment
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel
import com.cookandroid.instagram_android_moon.src.signup.name.GetNameFragment
import com.cookandroid.instagram_android_moon.src.signup.password.GetPasswordFragment
import com.cookandroid.instagram_android_moon.src.signup.phone.GetPhoneNumberFragment
import com.cookandroid.instagram_android_moon.src.signup.profileimage.AddProfileImageFragment
import com.cookandroid.instagram_android_moon.src.signup.save.SaveSignInFragment
import com.cookandroid.instagram_android_moon.src.signup.terms.GetAgreementOnTermsFragment
import com.cookandroid.instagram_android_moon.src.signup.username.GetUserNameFragment
import com.cookandroid.instagram_android_moon.src.welcome.WelcomeActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate), SignUpActivityInterface {
    lateinit var getBirthDateFragment: GetBirthDateFragment
    lateinit var getCertificationCodeFragment: GetCertificationCodeFragment
    lateinit var getEmailAddressFragment: GetEmailAddressFragment
    lateinit var getNameFragment: GetNameFragment
    lateinit var getPasswordFragment: GetPasswordFragment
    lateinit var getPhoneNumberFragment: GetPhoneNumberFragment
    lateinit var addProfileImageFragment: AddProfileImageFragment
    lateinit var saveSignInFragment: SaveSignInFragment
    lateinit var getAgreementOnTermsFragment: GetAgreementOnTermsFragment
    lateinit var getUserNameFragment: GetUserNameFragment
    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment Init
        fragmentInitInSignUp()

        changeFragment(R.id.fragment_container_sign_up, getPhoneNumberFragment)
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(X_ACCESS_TOKEN, response.result.jwt)
        editor.putString(LOGIN_USER_ID, response.result.user_id.toString())
        editor.apply()
        response.message?.let { showCustomToast(it) }
        val intent = Intent(this@SignUpActivity, WelcomeActivity::class.java)
        intent.putExtra("USER_NAME", signUpViewModel.nickname)
        finishAffinity()
        startActivity(intent)
    }

    override fun onPostSignUpFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("SignUpError", "$message")
    }

    private fun fragmentInitInSignUp() {
        getBirthDateFragment = GetBirthDateFragment()
        getCertificationCodeFragment = GetCertificationCodeFragment()
        getEmailAddressFragment = GetEmailAddressFragment()
        getNameFragment = GetNameFragment()
        getPasswordFragment = GetPasswordFragment()
        getPhoneNumberFragment = GetPhoneNumberFragment()
        addProfileImageFragment = AddProfileImageFragment()
        saveSignInFragment = SaveSignInFragment()
        getAgreementOnTermsFragment = GetAgreementOnTermsFragment()
        getUserNameFragment = GetUserNameFragment()
    }
}