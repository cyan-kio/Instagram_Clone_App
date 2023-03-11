package com.cookandroid.instagram_android_moon.src.signup.name

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetNameBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetNameFragment : BaseFragment<FragmentGetNameBinding>(
    FragmentGetNameBinding::bind, R.layout.fragment_get_name) {

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // 이름 입력
        binding.btnGetNameNext.setOnClickListener {
            signUpViewModel.apply {
                name = binding.edtGetName.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getPasswordFragment)
        }
    }
}