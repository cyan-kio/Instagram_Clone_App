package com.cookandroid.instagram_android_moon.src.post.posting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentPostingBinding

class PostingFragment : BaseFragment<FragmentPostingBinding>(FragmentPostingBinding::bind, R.layout.fragment_posting) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.supportFragmentManager?.setFragmentResultListener("URI_LIST_CHECKED", viewLifecycleOwner) { _, bundle ->
            bundle.getStringArrayList("uriList")?.let { uriList ->
                for(uri in uriList) {
                    Log.d("PostingFragment", uri.toString())
                }
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}