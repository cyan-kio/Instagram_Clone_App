package com.cookandroid.instagram_android_moon.src.signup.profileimage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentAddProfileImageBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.SignUpService
import com.cookandroid.instagram_android_moon.src.signup.model.PostEmailSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.PostPhoneSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class AddProfileImageFragment : BaseFragment<FragmentAddProfileImageBinding>(
    FragmentAddProfileImageBinding::bind, R.layout.fragment_add_profile_image){

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // 프로필 사진 추가
        binding.btnAddProfileImageAddImage.setOnClickListener {

//            navigatePhotos()
//            signUpViewModel.apply {
//                nickname = binding.edtGetUserName.text.toString()
//            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getAgreementOnTermsFragment)
        }

        binding.btnAddProfileImageAddSkip.setOnClickListener {
                val verify_method = signUpViewModel.verify_method
                val birth_date = signUpViewModel.birth_date
                val nickname = signUpViewModel.nickname
                val password = signUpViewModel.password
                val profile_image_url = signUpViewModel.profile_image_url
                when(signUpViewModel.signup_method){
                    0-> {
                        val postRequest = PostPhoneSignUpRequest(phone_number = verify_method, birth_date = birth_date
                            ,nickname = nickname, password = password, profile_image_url = profile_image_url)
                        SignUpService(signUpActivity).tryPostPhoneSignUp(postRequest)
                    }
                    1-> {
                        val postRequest = PostEmailSignUpRequest(email_address = verify_method, birth_date = birth_date
                            ,nickname = nickname, password = password, profile_image_url = profile_image_url)
                        SignUpService(signUpActivity).tryPostEmailSignUp(postRequest)
                    }
            }
        }
    }

    private fun navigatePhotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,2000)
    }
}