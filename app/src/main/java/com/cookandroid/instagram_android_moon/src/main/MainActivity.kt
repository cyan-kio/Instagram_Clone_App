package com.cookandroid.instagram_android_moon.src.main


import android.os.Bundle
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityMainBinding
import com.cookandroid.instagram_android_moon.src.main.explore.ExploreFragment
import com.cookandroid.instagram_android_moon.src.main.home.HomeFragment
import com.cookandroid.instagram_android_moon.src.main.post.PostFragment
import com.cookandroid.instagram_android_moon.src.main.profile.ProfileFragment
import com.cookandroid.instagram_android_moon.src.main.reels.ReelsFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var homeFragment: HomeFragment
    private lateinit var exploreFragment: ExploreFragment
    private lateinit var postFragment: PostFragment
    private lateinit var reelsFragment: ReelsFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentInitInMain()

        // btm_navi_item_tint
        binding.btmNaviMain.itemIconTintList = null

        changeFragment(R.id.container_main, homeFragment)
    }

    override fun onResume() {
        super.onResume()

        btmNaviItemSelect()
    }

    private fun fragmentInitInMain() {
        homeFragment = HomeFragment()
        exploreFragment = ExploreFragment()
        postFragment = PostFragment()
        reelsFragment = ReelsFragment()
        profileFragment = ProfileFragment()
    }

    fun btmNaviItemSelect() {
        binding.btmNaviMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.item_home -> {
                    changeFragment(R.id.container_main, homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.item_explore -> {
                    changeFragment(R.id.container_main, exploreFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.item_post -> {
                    changeFragment(R.id.container_main, postFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.item_reels -> {
                    changeFragment(R.id.container_main, reelsFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.item_profile -> {
                    changeFragment(R.id.container_main, profileFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }
}