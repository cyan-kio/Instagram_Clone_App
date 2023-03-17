package com.cookandroid.instagram_android_moon.src.signup.save

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentSaveSignInBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class SaveSignInFragment : BaseFragment<FragmentSaveSignInBinding>(
    FragmentSaveSignInBinding::bind, R.layout.fragment_save_sign_in) {

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        binding.tvSaveSignInInfoGuide.text = signUpViewModel.name+" "+resources.getString(R.string.save_sign_in_info_guide)

        // 로그인 정보 저장
        binding.btnSaveSignInInfoNow.setOnClickListener {
            signUpViewModel.apply {
                signin_state = true
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getBirthDateFragment)
        }
        // 나중에 하기
        binding.btnSaveSignInInfoLater.setOnClickListener {
            signUpViewModel.apply {
                signin_state = false
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getBirthDateFragment)
        }
    }
}

