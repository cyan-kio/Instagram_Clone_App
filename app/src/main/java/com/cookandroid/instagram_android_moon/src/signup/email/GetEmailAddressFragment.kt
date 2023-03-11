package com.cookandroid.instagram_android_moon.src.signup.email

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetEmailAddressBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetEmailAddressFragment : BaseFragment<FragmentGetEmailAddressBinding>(FragmentGetEmailAddressBinding::bind, R.layout.fragment_get_email_address) {
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    private val SIGNUP_FOR_EMAIL = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // Phone으로 전환
        binding.btnGetEmailAddressSignUpForPhoneNumber.setOnClickListener {
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getPhoneNumberFragment)
        }

        // email_address로 가입
        binding.btnGetEmailAddressNext.setOnClickListener {
            signUpViewModel.apply {
                signup_method = SIGNUP_FOR_EMAIL
                verify_method = binding.edtGetEmailAddress.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getNameFragment)
        }
    }
}