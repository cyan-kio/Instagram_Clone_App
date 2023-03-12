package com.cookandroid.instagram_android_moon.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.ApplicationClass.Companion.LOGIN_USER_ID
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentProfileBinding
import com.cookandroid.instagram_android_moon.src.main.profile.adapter.ProfilePagerAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import com.cookandroid.instagram_android_moon.src.main.profile.model.ResultProfile
import com.cookandroid.instagram_android_moon.src.main.profile.profilepager.ProfilePagerFragment
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile), ProfileFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Path_Argument
        val userId: String? = ApplicationClass.sSharedPreferences.getString(LOGIN_USER_ID, null)
        if(userId != null) {
            ProfileService(this).tryGetProfile(user_id = userId.toInt())
        }
        else Log.d("GetProfileError", "user_id is null")



        // ViewPager2
        val pagerAdapter = ProfilePagerAdapter(requireActivity())
        pagerAdapter.addFragment(ProfilePagerFragment(1))
        pagerAdapter.addFragment(ProfilePagerFragment(2))
        binding.viewpagerProfile.adapter = pagerAdapter
        binding.viewpagerProfile.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        // TabLayout
        TabLayoutMediator(binding.tabsProfile, binding.viewpagerProfile) { tab, position ->
            tab.customView = getTabView(position)
        }.attach()

        // 이미지 둥글게
        binding.ivProfileTopImage.clipToOutline = true

    }

    override fun onResume() {
        super.onResume()

        // Discover_People_layout_visibility
        binding.ckbxProfileTopDiscoverPeople.setOnCheckedChangeListener { buttonView, isChecked ->
            when(buttonView.isChecked) {
                true -> binding.constProfileDiscoverPeople.visibility = View.VISIBLE
                else -> binding.constProfileDiscoverPeople.visibility = View.GONE
            }
        }
    }

    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.item_tab_profile, null, false)
        val iv_Icon = view.findViewById<ImageView>(R.id.iv_tab_item_profile)

        when (position) {
            0 -> iv_Icon.setImageResource(R.drawable.selector_tabitem_icon_profile_feed_grid)
            else -> iv_Icon.setImageResource(R.drawable.selector_tabitem_icon_profile_tagged_grid)
        }
        return view
    }

    private fun profileInit(resultProfile: ResultProfile) {
        // profile_binding
        Glide.with(this).load(resultProfile.profile_image_url).into(binding.ivProfileTopImage)
        binding.apply {
            tvToolbarProfileUsername.text = resultProfile.nickname
            tvProfileUserInfoUserName.text =resultProfile.nickname
            tvProfileUserInfoIntroduction.text = resultProfile.introduce
            tvProfileTopPostingCount.text = resultProfile.post_count.toString()
            tvProfileTopFollowerCount.text = resultProfile.follower_count.toString()
            tvProfileTopFollowingCount.text = resultProfile.following_count.toString()
        }
    }


    override fun onGetProfileSuccess(response: ProfileResponse) {
        val resultProfile = response.result
        profileInit(resultProfile)
        response.message?.let { showCustomToast(it) }
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }



}