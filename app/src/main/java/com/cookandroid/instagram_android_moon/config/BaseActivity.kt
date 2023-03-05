package com.cookandroid.instagram_android_moon.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.src.main.explore.ExploreFragment
import com.cookandroid.instagram_android_moon.src.main.home.HomeFragment
import com.cookandroid.instagram_android_moon.src.main.post.PostFragment
import com.cookandroid.instagram_android_moon.src.main.profile.ProfileFragment
import com.cookandroid.instagram_android_moon.src.main.reels.ReelsFragment

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
        protected lateinit var binding: B
            private set
        lateinit var homeFragment: HomeFragment
        lateinit var exploreFragment: ExploreFragment
        lateinit var postFragment: PostFragment
        lateinit var reelsFragment: ReelsFragment
        lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)

        homeFragment = HomeFragment()
        exploreFragment = ExploreFragment()
        postFragment = PostFragment()
        reelsFragment = ReelsFragment()
        profileFragment = ProfileFragment()

        setContentView(binding.root)
    }

    fun changeFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commitAllowingStateLoss()
    }
}