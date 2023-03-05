package com.cookandroid.instagram_android_moon.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityMainBinding
import com.cookandroid.instagram_android_moon.databinding.ActivitySplashBinding
import kotlin.math.exp

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btmNaviMain.itemIconTintList = null

        changeFragment(R.id.container_main, homeFragment)
    }

    override fun onResume() {
        super.onResume()

        btmNaviItemSelect()
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