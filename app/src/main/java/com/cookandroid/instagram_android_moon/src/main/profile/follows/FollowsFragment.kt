package com.cookandroid.instagram_android_moon.src.main.profile.follows

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentFollowsBinding
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.adapter.FollowsPagerAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.FollowersFragment
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.FollowingsFragment
import com.google.android.material.tabs.TabLayoutMediator

class FollowsFragment : BaseFragment<FragmentFollowsBinding>(FragmentFollowsBinding::bind, R.layout.fragment_follows) {
    private lateinit var followerFragment: FollowersFragment
    private lateinit var followingFragment: FollowingsFragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentInitInFollows()

        val followsAdapter = FollowsPagerAdapter(requireActivity()).apply{
            addFragment(followerFragment)
            addFragment(followingFragment)
        }

        binding.viewpagerFollows.apply{
            adapter = followsAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e("ViewPagerFragment", "Page ${position+1}")
                }
            })
        }

        if(arguments!=null) {
            val followerCount = arguments?.getInt("followerCount", 0)
            val followingCount = arguments?.getInt("followingCount", 0)
            val replaceState = arguments?.getInt("replaceState", 0)

            val tabTextArray = arrayOf("팔로워 ${followerCount}명", "팔로잉 ${followingCount}명")
            TabLayoutMediator(binding.tabsFollows, binding.viewpagerFollows) { tab, position ->
                tab.text = tabTextArray[position]
            }.attach()

            binding.tabsFollows.apply {
                if (replaceState == 0) {
                    binding.viewpagerFollows.currentItem = 0
//                    selectTab(binding.tabsFollows.getTabAt(0))
                }
                else {
                    binding.viewpagerFollows.currentItem = 1
//                    selectTab(binding.tabsFollows.getTabAt(1))
                }
            }
        }
    }

    // fragment_Init
    private fun fragmentInitInFollows() {
        followerFragment = FollowersFragment()
        followingFragment = FollowingsFragment()
    }
}

