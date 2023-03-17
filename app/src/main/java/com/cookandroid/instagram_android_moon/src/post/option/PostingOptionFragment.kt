package com.cookandroid.instagram_android_moon.src.post.option

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentPostingBinding
import com.cookandroid.instagram_android_moon.databinding.FragmentPostingOptionBinding
import com.cookandroid.instagram_android_moon.src.post.model.PostViewModel
import com.cookandroid.instagram_android_moon.src.post.model.image.ImagePickerViewModel
import com.cookandroid.instagram_android_moon.src.post.posting.PostingFragment

class PostingOptionFragment : BaseFragment<FragmentPostingOptionBinding>(FragmentPostingOptionBinding::bind, R.layout.fragment_posting_option) {
    private val postViewModel: PostViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchHideLike.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked) {
                true -> postViewModel.likeShowStatus = 1
                false -> postViewModel.likeShowStatus = 0
            }
        }

        binding.switchHideComment.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked) {
                true -> postViewModel.commentShowStatus = 1
                false -> postViewModel.commentShowStatus = 0
            }
        }

    }
}