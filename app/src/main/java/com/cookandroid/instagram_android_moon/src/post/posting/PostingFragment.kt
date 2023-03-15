package com.cookandroid.instagram_android_moon.src.post.posting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentPostingBinding
import com.cookandroid.instagram_android_moon.databinding.FragmentSelectBinding

class PostingFragment : BaseFragment<FragmentPostingBinding>(FragmentPostingBinding::bind, R.layout.fragment_posting) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}