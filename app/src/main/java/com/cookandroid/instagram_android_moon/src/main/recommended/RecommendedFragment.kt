package com.cookandroid.instagram_android_moon.src.main.recommended

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentRecommendedBinding
import com.cookandroid.instagram_android_moon.src.main.recommended.adapter.RecommendedGridAdapter
import com.cookandroid.instagram_android_moon.src.main.recommended.adapter.SearchUserAdapter
import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.main.recommended.model.SearchUserResponse
import com.cookandroid.instagram_android_moon.util.GridSpaceItemDecoration

class RecommendedFragment : BaseFragment<FragmentRecommendedBinding>(FragmentRecommendedBinding::bind, R.layout.fragment_recommended), RecommendedFragmentInterface {
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RecommendedService(this).tryGetRecommended()

        binding.edtRecommendedSearch.setOnTouchListener { edt, event ->
            // const_setMargin
            val layoutParams = ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, changeDP(35))
            layoutParams.marginStart = changeDP(40)
            binding.constRecommendedEdtBackground.layoutParams = layoutParams
            // iv_search src change
            Glide.with(this).load(R.drawable.img_search_icon_touched).into(binding.ivSearchIcon)
            // btn_back visible
            binding.btnRecommendedBack.visibility = View.VISIBLE
            // recycler_recommended visible
            binding.recyclerRecommendedGrid.visibility = View.GONE
            // edt_search give focus
            edt.requestFocus()
            val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(edt, 0)

            true
        }

        binding.btnRecommendedBack.setOnClickListener {
            // const_setMargin
            val layoutParams = ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, changeDP(35))
            layoutParams.marginStart = changeDP(0)
            binding.constRecommendedEdtBackground.layoutParams = layoutParams
            // iv_search src change
            Glide.with(this).load(R.drawable.img_search_icon).into(binding.ivSearchIcon)
            // btn_back visible
            binding.btnRecommendedBack.visibility = View.GONE
            // recycler_recommended visible
            binding.recyclerRecommendedGrid.visibility = View.VISIBLE
            // recycler_search visible
            binding.recyclerRecommendedSearchUser.visibility = View.GONE
        }

        binding.edtRecommendedSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s == null || s.toString().replace("\\s".toRegex(), "") == "") {
                    // recycler_search visible
                    binding.recyclerRecommendedSearchUser.visibility = View.GONE
                    binding.viewLine.visibility = View.GONE
                } else {
                    // recycler_search visible
                    RecommendedService(this@RecommendedFragment).tryGetSearchUser(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        )
    }

    private fun changeDP(value : Int) : Int{
        val displayMetrics = resources.displayMetrics
        val dp = Math.round(value * displayMetrics.density)
        return dp
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

    override fun onGetSearchUserSuccess(response: SearchUserResponse) {
        val resultSearchUser = response.result
        if(resultSearchUser.isNotEmpty()) {
            binding.recyclerRecommendedSearchUser.apply {
                adapter = SearchUserAdapter(requireContext(), resultSearchUser)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                response.message?.let { showCustomToast(it) }
            }
            binding.recyclerRecommendedSearchUser.visibility = View.VISIBLE
            binding.viewLine.visibility = View.VISIBLE
        } else {
            binding.recyclerRecommendedSearchUser.visibility = View.GONE
            binding.viewLine.visibility = View.GONE
        }
    }

    override fun onGetSearchUserFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}