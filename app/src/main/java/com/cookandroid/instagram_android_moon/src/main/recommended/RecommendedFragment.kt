package com.cookandroid.instagram_android_moon.src.main.recommended

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentRecommendedBinding
import com.cookandroid.instagram_android_moon.src.main.recommended.adapter.RecommendedGridAdapter
import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.util.GridSpaceItemDecoration

class RecommendedFragment : BaseFragment<FragmentRecommendedBinding>(FragmentRecommendedBinding::bind, R.layout.fragment_recommended), RecommendedFragmentInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RecommendedService(this).tryGetRecommended()
    }

    override fun onGetRecommendedSuccess(response: RecommendedResponse) {
        val resultRecommended = response.result
        binding.recyclerRecommendedGrid.apply {
            adapter = RecommendedGridAdapter(requireContext(), resultRecommended)
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            this.run {
                addItemDecoration(GridSpaceItemDecoration(3,10))
            }
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onGetRecommendedFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}