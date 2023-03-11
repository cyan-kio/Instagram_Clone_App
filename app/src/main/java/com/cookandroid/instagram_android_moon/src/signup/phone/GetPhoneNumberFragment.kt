package com.cookandroid.instagram_android_moon.src.signup.phone


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetPhoneNumberBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetPhoneNumberFragment : BaseFragment<FragmentGetPhoneNumberBinding>(FragmentGetPhoneNumberBinding::bind, R.layout.fragment_get_phone_number) {
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    private val SIGNUP_FOR_PHONE = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // Email로 전환
        binding.btnGetPhoneNumberSignUpForEmail.setOnClickListener {
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getEmailAddressFragment)
        }

        // phone_num으로 가입
        binding.btnGetPhoneNumberNext.setOnClickListener {
            signUpViewModel.apply {
                signup_method = SIGNUP_FOR_PHONE
                verify_method = binding.edtGetPhoneNumber.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getNameFragment)
        }
    }
}