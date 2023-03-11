package com.cookandroid.instagram_android_moon.src.signup.terms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetAgreementOnTermsBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class GetAgreementOnTermsFragment : BaseFragment<FragmentGetAgreementOnTermsBinding>(
    FragmentGetAgreementOnTermsBinding::bind, R.layout.fragment_get_agreement_on_terms) {

        private val signUpViewModel: SignUpViewModel by activityViewModels()
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val signUpActivity = activity as SignUpActivity


            binding.btnGetAgreementOnTermsSelectAll.setOnClickListener {
                binding.ckbxTermsOfUseEssential.isChecked = true
                binding.ckbxPrivacyPolicyEssential.isChecked = true
                binding.ckbxLocationBasedFeaturesEssential.isChecked = true
            }

            // 동의 버튼
            binding.btnGetAgreementOnTermsNext.setOnClickListener {
                if(binding.ckbxPrivacyPolicyEssential.isChecked && binding.ckbxTermsOfUseEssential.isChecked && binding.ckbxLocationBasedFeaturesEssential.isChecked)
                    signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.addProfileImageFragment)
            }
    }
}