package com.cookandroid.instagram_android_moon.src.main.profile.other

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentOtherProfileBinding
import com.cookandroid.instagram_android_moon.src.main.profile.ProfileFragmentInterface
import com.cookandroid.instagram_android_moon.src.main.profile.ProfileService
import com.cookandroid.instagram_android_moon.src.main.profile.adapter.ProfilePagerAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionInterface
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionService
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import com.cookandroid.instagram_android_moon.src.main.profile.model.ResultProfile
import com.cookandroid.instagram_android_moon.src.main.profile.other.pager.OtherProfileFeedsFragment
import com.cookandroid.instagram_android_moon.src.main.profile.other.pager.OtherProfileTaggedFragment
import com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds.ProfileFeedsFragment
import com.cookandroid.instagram_android_moon.src.main.profile.pager.tagged.ProfileTaggedFragment
import com.google.android.material.tabs.TabLayoutMediator

class OtherProfileFragment(private val userId: Int) :
    BaseFragment<FragmentOtherProfileBinding>(FragmentOtherProfileBinding::bind, R.layout.fragment_other_profile),
    ProfileFragmentInterface, FollowFunctionInterface {
    private lateinit var profileFeedsFragment: ProfileFeedsFragment
    private lateinit var profileTaggedFragment: ProfileTaggedFragment
    private lateinit var followsFragment: com.cookandroid.instagram_android_moon.src.main.profile.follows.FollowsFragment
    private lateinit var myUserId : String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragments Init
        fragmentInitInProfile()
        val myUserIdgiven: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.LOGIN_USER_ID, null)
        if (myUserIdgiven != null) myUserId = myUserIdgiven
        // path_Argument
        ProfileService(this).tryGetProfile(user_id = userId)


        // viewPager2
        val pagerAdapter = ProfilePagerAdapter(requireActivity()).apply {
            addFragment(OtherProfileFeedsFragment(userId))
            addFragment(OtherProfileTaggedFragment())
        }
        binding.viewpagerOtherProfile.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e("ViewPagerFragment", "Page ${position + 1}")
                }
            })
        }

        // tabLayout
        TabLayoutMediator(binding.tabsOtherProfile, binding.viewpagerOtherProfile) { tab, position ->
            tab.customView = getTabView(position)
        }.attach()

        // image_Round_Setting
        binding.ivOtherProfileTopImage.clipToOutline = true

    }

    override fun onResume() {
        super.onResume()

        // Discover_People_layout_visibility
        binding.ckbxOtherProfileTopDiscoverPeople.setOnCheckedChangeListener { buttonView, isChecked ->
            when (buttonView.isChecked) {
                true -> binding.constOtherProfileDiscoverPeople.visibility = View.VISIBLE
                else -> binding.constOtherProfileDiscoverPeople.visibility = View.GONE
            }
        }
    }

    // fragment_Init
    private fun fragmentInitInProfile() {
        profileFeedsFragment = ProfileFeedsFragment()
        profileTaggedFragment = ProfileTaggedFragment()
        followsFragment =
            com.cookandroid.instagram_android_moon.src.main.profile.follows.FollowsFragment()
    }

    // tab_Item_Setting
    private fun getTabView(position: Int): View {
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.item_tab_profile, null, false)
        val iv_Icon = view.findViewById<ImageView>(R.id.iv_tab_item_profile)

        when (position) {
            0 -> iv_Icon.setImageResource(R.drawable.selector_tabitem_icon_profile_feed_grid)
            else -> iv_Icon.setImageResource(R.drawable.selector_tabitem_icon_profile_tagged_grid)
        }
        return view
    }

    // profile_Binding
    private fun profileBinding(resultProfile: ResultProfile) {
        Glide.with(this).load(resultProfile.profile_image_url).into(binding.ivOtherProfileTopImage)
        binding.apply {
            tvToolbarOtherProfileUsername.text = resultProfile.nickname
            tvOtherProfileUserInfoName.text = resultProfile.name
            tvOtherProfileUserInfoIntroduction.text = resultProfile.introduce
            tvOtherProfileTopPostingCount.text = resultProfile.post_count.toString()
            tvOtherProfileTopFollowerCount.text = resultProfile.follower_count.toString()
            tvOtherProfileTopFollowingCount.text = resultProfile.following_count.toString()
            // 알고 있는 팔로우 친구
            if(resultProfile.connected_count == 1) {
                constOtherProfileConnected.visibility = View.VISIBLE
                Glide.with(this@OtherProfileFragment).load(resultProfile.connected_friend_profiles[0].profile_image_url).into(binding.ivOtherProfileImgOne)
                ivOtherProfileImgOne.clipToOutline = true
                val span = SpannableStringBuilder(resultProfile.connected_friend_profiles[0].nickname + "님이 팔로우합니다")
                span.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    resultProfile.connected_friend_profiles[0].nickname.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.tvOtherProfileUserConnected.text = span
            } else if (resultProfile.connected_count > 1) {
                constOtherProfileConnected.visibility = View.VISIBLE
                ivOtherProfileImgTwo.visibility = View.VISIBLE
                Glide.with(this@OtherProfileFragment).load(resultProfile.connected_friend_profiles[0].profile_image_url).into(binding.ivOtherProfileImgOne)
                Glide.with(this@OtherProfileFragment).load(resultProfile.connected_friend_profiles[1].profile_image_url).into(binding.ivOtherProfileImgTwo)
                ivOtherProfileImgOne.clipToOutline = true
                ivOtherProfileImgTwo.clipToOutline = true
                var span = SpannableStringBuilder()
                if (resultProfile.connected_count == 2) {
                    span = SpannableStringBuilder(resultProfile.connected_friend_profiles[0].nickname+"님, "+ resultProfile.connected_friend_profiles[1].nickname + "님이 팔로우합니다")
                    span.setSpan(
                        StyleSpan(Typeface.BOLD),
                        0,
                        resultProfile.connected_friend_profiles[0].nickname.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    span.setSpan(
                        StyleSpan(Typeface.BOLD),
                        resultProfile.connected_friend_profiles[0].nickname.length+3,
                        resultProfile.connected_friend_profiles[0].nickname.length+3+resultProfile.connected_friend_profiles[1].nickname.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else {
                    span = SpannableStringBuilder(resultProfile.connected_friend_profiles[0].nickname+"님, "+ resultProfile.connected_friend_profiles[1].nickname + "님 외 " + (resultProfile.connected_count-2)+"명이 팔로우합니다")
                    span.setSpan(
                        StyleSpan(Typeface.BOLD),
                        0,
                        resultProfile.connected_friend_profiles[0].nickname.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    span.setSpan(
                        StyleSpan(Typeface.BOLD),
                        resultProfile.connected_friend_profiles[0].nickname.length+3,
                        resultProfile.connected_friend_profiles[0].nickname.length+3+resultProfile.connected_friend_profiles[1].nickname.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    span.setSpan(
                        StyleSpan(Typeface.BOLD),
                        resultProfile.connected_friend_profiles[0].nickname.length+3+resultProfile.connected_friend_profiles[1].nickname.length+4,
                        resultProfile.connected_friend_profiles[0].nickname.length+3+resultProfile.connected_friend_profiles[1].nickname.length+4+resultProfile.connected_count.toString().length+1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                binding.tvOtherProfileUserConnected.text = span
            }
            // 팔로우 여부
            if(resultProfile.follow_status == 1) {
                linearOtherProfileFollowing.visibility = View.VISIBLE
            } else {
                linearOtherProfileFollow.visibility = View.VISIBLE
            }
            binding.btnOtherProfileFollow.setOnClickListener {
                FollowFunctionService(this@OtherProfileFragment).tryFollow(
                    myUserId.toInt(),
                    resultProfile.user_id)
            }
            binding.btnOtherProfileFollowing.setOnClickListener {
                FollowFunctionService(this@OtherProfileFragment).tryUnFollow(myUserId.toInt(), resultProfile.user_id)

            }
        }

        binding.linearOtherProfileTopFollowerCount.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("followerCount", resultProfile.follower_count)
            bundle.putInt("followingCount", resultProfile.following_count)
            bundle.putInt("replaceState", 0)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            followsFragment.arguments = bundle
            transaction.replace(R.id.container_main, followsFragment).addToBackStack(null).commit()
        }
        binding.linearOtherProfileTopFollowingCount.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("followerCount", resultProfile.follower_count)
            bundle.putInt("followingCount", resultProfile.following_count)
            bundle.putInt("replaceState", 1)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            followsFragment.arguments = bundle
            transaction.replace(R.id.container_main, followsFragment).addToBackStack(null).commit()
        }
    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        val resultProfile = response.result
        profileBinding(resultProfile)
        response.message?.let { showCustomToast(it) }
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }

    override fun onPostFollowSuccess(response: FollowResponse) {
        binding.linearOtherProfileFollow.visibility = View.GONE
        binding.linearOtherProfileFollowing.visibility = View.VISIBLE
        response.message?.let { showCustomToast(it) }
    }

    override fun onPostFollowFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }

    override fun onPostUnFollowSuccess(response: FollowResponse) {
        binding.linearOtherProfileFollowing.visibility = View.GONE
        binding.linearOtherProfileFollow.visibility = View.VISIBLE
        response.message?.let { showCustomToast(it) }
    }

    override fun onPostUnFollowFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}
