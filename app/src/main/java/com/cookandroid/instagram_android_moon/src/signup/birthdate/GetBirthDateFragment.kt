package com.cookandroid.instagram_android_moon.src.signup.birthdate

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentGetBirthDateBinding
import com.cookandroid.instagram_android_moon.src.signup.SignUpActivity
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel
import java.text.SimpleDateFormat
import java.util.*

class GetBirthDateFragment : BaseFragment<FragmentGetBirthDateBinding>(
    FragmentGetBirthDateBinding::bind, R.layout.fragment_get_birth_date) {

    var formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpActivity = activity as SignUpActivity

        // 생년월일 입력
        binding.btnGetBirthDateNext.setOnClickListener {
            signUpViewModel.apply {
                birth_date = binding.tiedtSignInEmail.text.toString()
            }
            signUpActivity.changeFragmentWithAddBackStack(R.id.fragment_container_sign_up, signUpActivity.getUserNameFragment)
        }

        binding.tiedtSignInEmail.setOnClickListener {
            val getDate: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, DatePickerDialog.OnDateSetListener { datepicker, year, month, dayOfMonth ->

                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = formatDate.format(selectDate.time)
                binding.tiedtSignInEmail.setText(date)

            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }


    }

}