package com.cookandroid.instagram_android_moon.src.post.posting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentPostingBinding
import com.cookandroid.instagram_android_moon.src.main.MainActivity
import com.cookandroid.instagram_android_moon.src.post.PostActivity
import com.cookandroid.instagram_android_moon.src.post.model.PhotoPost
import com.cookandroid.instagram_android_moon.src.post.model.PostPostRequest
import com.cookandroid.instagram_android_moon.src.post.model.PostResponse
import com.cookandroid.instagram_android_moon.src.post.model.PostViewModel
import com.cookandroid.instagram_android_moon.src.post.option.PostingOptionFragment
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpViewModel

class PostingFragment : BaseFragment<FragmentPostingBinding>(FragmentPostingBinding::bind, R.layout.fragment_posting), PostingFragmentInterface {
    val postActivity = PostActivity()
    private val postViewModel: PostViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.supportFragmentManager?.setFragmentResultListener("URI_LIST_CHECKED", viewLifecycleOwner) { _, bundle ->
            bundle.getStringArrayList("uriList")?.let { uriList ->
                var i = 0
                for(uri in uriList) {
                    Log.d("PostingFragment", uri.toString())
                    postViewModel.photos.add(0, PhotoPost(uri.toString(), mutableListOf("")))
                    i++
                }
            }
        }

        binding.linearPostingOption.setOnClickListener {
            activity?.supportFragmentManager?.apply{
                beginTransaction()
                    .replace(R.id.container_post, PostingOptionFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.btnToolbarPostingTopPosting.setOnClickListener {
            val postRequest = PostPostRequest(content = binding.edtPostingContent.text.toString(),
            place = "", likeShowStatus = postViewModel.likeShowStatus, commentShowStatus = postViewModel.commentShowStatus,
            photos = postViewModel.photos, tagphotosord = postViewModel.tagphotosord)

            PostingService(this).tryPostPost(postRequest)
        }

    }

    override fun onPostPostSuccess(response: PostResponse) {
        startActivity(
            Intent(requireActivity(), MainActivity::class.java))
        response.message?.let { showCustomToast(it) }
        requireActivity().finish()
    }

    override fun onPostPostFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("SignUpError", message)
    }
}