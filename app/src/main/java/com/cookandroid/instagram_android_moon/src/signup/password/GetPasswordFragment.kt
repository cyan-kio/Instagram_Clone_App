package com.cookandroid.instagram_android_moon.src.signup.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetPasswordBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetPasswordFragment : BaseFragment<FragmentGetPasswordBinding>(
    FragmentGetPasswordBinding::bind, R.layout.fragment_get_password) {

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // 비밀번호 입력
        binding.btnGetPasswordNext.setOnClickListener {
            signUpViewModel.apply {
                password = binding.edtGetPassword.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.saveSignInFragment)
        }
    }
}