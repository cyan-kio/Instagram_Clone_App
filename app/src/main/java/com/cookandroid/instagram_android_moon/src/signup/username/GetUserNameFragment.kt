package com.cookandroid.instagram_android_moon.src.signup.username

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetUserNameBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetUserNameFragment : BaseFragment<FragmentGetUserNameBinding>(
    FragmentGetUserNameBinding::bind, R.layout.fragment_get_user_name) {

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // 사용자 이름 입력
        binding.btnGetUserNameNext.setOnClickListener {
            signUpViewModel.apply {
                nickname = binding.edtGetUserName.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getAgreementOnTermsFragment)
        }

    }
}