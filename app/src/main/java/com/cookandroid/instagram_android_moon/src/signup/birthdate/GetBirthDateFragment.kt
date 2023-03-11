package com.cookandroid.instagram_android_moon.src.signup.birthdate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetBirthDateBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetBirthDateFragment : BaseFragment<FragmentGetBirthDateBinding>(
    FragmentGetBirthDateBinding::bind, R.layout.fragment_get_birth_date) {

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // 생년월일 입력
        binding.btnGetBirthDateNext.setOnClickListener {
            signUpViewModel.apply {
                birth_date = binding.edtGetBirthDate.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getUserNameFragment)
        }

    }

}